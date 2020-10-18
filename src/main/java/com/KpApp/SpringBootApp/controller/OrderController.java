package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Order;
import com.KpApp.SpringBootApp.service.OrderService;
import com.KpApp.SpringBootApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("table/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrder(String id, Model model){
        Iterable<Order> orders = orderService.findOrders(id);
        model.addAttribute("orders",orders);
        return "order";

    }

    @PostMapping("/add")
    public String addOrder(Order order, @RequestParam(name = "product_type_enum") String product_type_enum,
                          @RequestParam(required = false,name="order_schema_image") MultipartFile file,
                          @RequestParam(required = true,name="customer_username") String customer_username,
                          @RequestParam(required = false,name="manager_username") String manager_username,
                          @RequestParam(required = false,name="planed_date_end_str") String planed_date_end_str,
                          Model model){
        orderService.addOrder(order,product_type_enum,file,customer_username,manager_username,planed_date_end_str);
        return "redirect:/table/order";
    }
    @PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
    @PostMapping("/edit")
    public String editOrder(Order order,@RequestParam(name = "product_type_enum",required = false) String Order_role_enum,
                           @RequestParam(name="order_schema_image",required = false) MultipartFile file,
                            @RequestParam(required = true,name="customer_username") String customer_username,
                            @RequestParam(required = false,name="manager_username") String manager_username,
                            @RequestParam(required = false,name="planed_date_end_str") String planed_date_end,
                           Model model){
        orderService.editOrder(order,Order_role_enum,file,customer_username,manager_username,planed_date_end);
        return "redirect:/table/order";
    }
    @PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
    @PostMapping("/delete")
    public String deleteOrder(Order order,Model model){
        orderService.deleteOrder(order);
        return "redirect:/table/order";
    }

    @GetMapping("/{id}/{file_name}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("file_name") String fileName,
                                                       HttpServletResponse response,
                                                       @PathVariable Long id) throws IOException {
        InputStream photo_is = orderService.getFile(id, fileName);
        if(photo_is==null){
            throw new IOException("IOError open file to input stream");
        }
        try{
            return ResponseEntity.ok()
                    .contentLength(photo_is.available())
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(photo_is));
        }catch (IOException e){
            throw new IOException("IOError writing file to output stream");
        }
    }
}
