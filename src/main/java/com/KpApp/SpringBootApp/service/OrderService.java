package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Order;
import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.User;
import com.KpApp.SpringBootApp.repo.OrderRepo;
import com.KpApp.SpringBootApp.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserService userService;

    @Value("${upload_path}")
    private String upload_path;


    public boolean isExist(Order Order){
        return orderRepo.findById(Order.getId()) != null;
    }

    public Iterable<Order> findOrders(String id) {
        Iterable<Order> orders;
        if(id!=null && !id.isEmpty()){
            try {
                long idL = Long.parseLong(id);
                ArrayList list = new ArrayList();
                Order byId = orderRepo.findById(Long.parseLong(id));
                if(byId!=null){
                    list.add(byId);
                }
                list.add(orderRepo.findById(idL));
                orders = list;
            }catch (Exception e){
                orders = new ArrayList<Order>();
            }
        }else {
            orders = orderRepo.findAll();
        }
        return orders;
    }

    public boolean addOrder(Order order, String product_type_enum, MultipartFile file,
                            String customer_username,String manager_username,
                            String planed_date_end_str) {
        if(order==null) return false;

        if(isExist(order)){
            System.out.println("Order AlREADY EXISTS");
            return false;
        }

        try{
            order.setProduct_type(Product_type.valueOf(product_type_enum));
            Date simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(planed_date_end_str);
            order.setPlaned_date_end(simpleDateFormat);
            LocalDate localDate = LocalDate.now();
            order.setOrder_date(new Date());
            if(customer_username==null || customer_username.isEmpty()){return false;}
            User customer = userService.findUser(customer_username);
            if(customer!=null){
                order.setCustomer(customer);
            }
            if(manager_username!=null){
                User manager = userService.findUser(manager_username);
                if(manager==null){ return false;}
                order.setManager(manager);
            }
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(order,file);
                }else{
                    return false;
                }
            }
            orderRepo.save(order);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean addOrder(Order order){
        if(order==null) return false;
        if(isExist(order)){
            System.out.println("Order AlREADY EXISTS");
            return false;
        }
        orderRepo.save(order);
        return true;
    }

    public boolean editOrder(Order order,String product_type_enum,MultipartFile file,String customer_username,String manager_username,
                             String planed_date_end_str){
        if(order==null) return false;

        if(!isExist(order)){
            System.out.println("Order NOT EXISTS");
            return false;
        }
        try {
            if(product_type_enum!=null){
                order.setProduct_type(Product_type.valueOf(product_type_enum));
            }
            if(customer_username!=null || !customer_username.isEmpty()){
                User customer = userService.findUser(customer_username);
                if(customer==null){ return false; }
                order.setCustomer(customer);
            }
            if(manager_username!=null){
                User manager = userService.findUser(manager_username);
                if(manager==null){ return false;}
                order.setManager(manager);
            }
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(order,file);
                }else{
                    return false;
                }
            }else{
                order.setOrder_scheme_name(orderRepo.findById(order.getId()).get().getOrder_scheme_name());
            }
            if(planed_date_end_str==null){
                order.setPlaned_date_end(orderRepo.findById(order.getId()).get().getPlaned_date_end());
            }else{
                Date simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(planed_date_end_str);
                order.setPlaned_date_end(simpleDateFormat);
            }
            orderRepo.save(order);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean editOrder(Order order){
        if(order==null) return false;

        if(!isExist(order)){
            System.out.println("Order NOT EXISTS");
            return false;
        }
        try {
            orderRepo.save(order);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean deleteOrder(Order order){
        if(order==null) return false;

        if(!isExist(order)){
            System.out.println("Order NOT EXISTS");
            return false;
        }
        orderRepo.delete(order);
        return true;
    }

    public Order saveFile(Order order,MultipartFile file) throws IOException {
        if(file!=null){
            File up_dir = new File(upload_path);
            if(!up_dir.exists()){
                if(!up_dir.mkdir()){
                    throw new IOException("Unable to create directory");
                }
            }
            String uuid = UUID.randomUUID().toString();
            String result_photo_name = uuid+"."+order.getId()+"."+file.getOriginalFilename();
            order.setOrder_scheme_name(result_photo_name);
            file.transferTo(Paths.get(upload_path+result_photo_name));
            return order;
        }
        return null;
    }

    public InputStream getFile(Long id, String filename){
        InputStream is;
        try {
            is = new BufferedInputStream(new FileInputStream(upload_path+filename));
        } catch (IOException ex) {
            return null;
        }
        return is;
    }
}
