package com.example.demo.controller;

import com.example.demo.entity.SectionsEntity;
import com.example.demo.model.UserEntity;
import com.example.demo.services.ProductService;
import com.example.demo.services.SectionsService;
import com.example.demo.services.UserService;
import com.example.demo.servlets.Login;
import com.example.demo.servlets.Register;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import com.example.demo.entity.ProductsEntity;
import com.example.demo.services.ProductService;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private ProductService productService;
    @Autowired
    private SectionsService sectionsService;

    @Autowired
    private UserService userService;

    @PostMapping(path = {"/Login"})
    public String afterloginpage(Model model, HttpServletRequest request, HttpServletResponse response) {
        Login login = new Login();
        try {
            return login.Response(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping(path = "/Registration")
    public String registrationpage(Model model) {
        return "register";
    }

    @PostMapping(path = "/register")
    public String afterregistrationpage(Model model, HttpServletRequest request, HttpServletResponse response) {
        Register reg = new Register();
        try {
            return reg.Registration(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("profile/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String admin(Model model, Authentication authentication) {
        model.addAttribute("allUsers", userService.findAllUsers());
        return "welcomeadmin2";
    }

    @GetMapping("profile/user")
    @PreAuthorize("hasAuthority('user') || hasAuthority('admin')")
    public String user(Model model, Authentication authentication) {
        model.addAttribute("allSections", sectionsService.loadAllSections());
        model.addAttribute("allProducts", productService.loadAllBooks());
        return "welcomeuser";
    }

    @GetMapping("/profile/allProducts/add")
    public String addPage(Model model) {
        model.addAttribute("user", new ProductsEntity());
        return "AEditPage";
    }

    @PostMapping("/profile/allProducts/add")
    public String addUser(@ModelAttribute ProductsEntity user, Model model) {

        model.addAttribute("allProducts", productService.loadAllBooks());
        productService.addProduct(user);
        return "redirect:/profile/user";
    }

    @PostMapping("/profile/allSections/add")
    public String addUser3(@ModelAttribute SectionsEntity user2, Model model) {

        sectionsService.addProduct(user2);
        model.addAttribute("allSections", sectionsService.loadAllSections());
        return "redirect:/profile/user";
    }

    @GetMapping("/profile/allSections/add")
    public String addPage3(Model model) {
        model.addAttribute("user2", new SectionsEntity());
        return "UEditPage";
    }

    @GetMapping("/profile/allSections/delete")
    public String deleteUser(@RequestParam Integer id, Model model) {
        model.addAttribute("user", sectionsService.getById(id));
        sectionsService.Delete(sectionsService.getById(id));
        return "redirect:/profile/user";
    }

    @GetMapping("/profile/allProducts/delete")
    public String deleteUser2(@RequestParam Integer id, Model model) {
        model.addAttribute("user", productService.getById(id));
        productService.Delete(productService.getById(id));
        return "redirect:/profile/user";
    }

    @GetMapping("/profile/allSections/edit")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", sectionsService.getById(id));
        return "UEditPage2";
    }

    @PostMapping("/profile/allSections/edit")
    public String confirmEditUser(@ModelAttribute SectionsEntity user) {
        int t = user.getSectionId();
        System.out.println(t);
        SectionsEntity tmpUser = sectionsService.getById(t);
        System.out.println(tmpUser.getSection());
        tmpUser.setSection(user.getSection());
        sectionsService.addProduct(tmpUser);

        return "redirect:/profile/user";
    }


    @GetMapping("/profile/allProducts/edit")
    public String editUser2(@RequestParam int id, Model model) {
        model.addAttribute("user", productService.getById(id));
        return "AEditPage2";
    }

    @PostMapping("/profile/allProducts/edit")
    public String confirmEditUser2(@ModelAttribute ProductsEntity user) {

        ProductsEntity tmpUser = productService.getById((int) user.getProductId());
        tmpUser.setProduct(user.getProduct());
        tmpUser.setSectionId(user.getSectionId());
        productService.addProduct(tmpUser);

        return "redirect:/profile/user";
    }



    @PostMapping("/profile/allUsers/add")
    public String addUser4(@ModelAttribute UserEntity user2, Model model) {

        userService.addUser(user2);
        model.addAttribute("allUsers", userService.findAllUsers());
        return "redirect:/profile/admin";
    }

    @GetMapping("/profile/allUsers/add")
    public String addUser5(Model model) throws SQLException {
        model.addAttribute("user3", new UserEntity());
        return "UserAddPage";
    }

    @GetMapping("/profile/allUsers/delete")
    public String deleteUser3(@RequestParam Integer id, Model model) {
        model.addAttribute("user", userService.getById(id));
        userService.safeDelete(userService.getById(id));
        return "redirect:/profile/admin";
    }

    @GetMapping("/profile/allUsers/edit")
    public String editUser5(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "UserEditPage";
    }

    @PostMapping("/profile/allUsers/edit")
    public String confirmEditUser6(@ModelAttribute UserEntity user) {

        UserEntity tmpUser = userService.getById((int) user.getIdUser());
        tmpUser.setName(user.getName());
        tmpUser.setPassword(user.getPassword());
        tmpUser.setEmail(user.getEmail());
        tmpUser.setRole(user.getRole());
        tmpUser.setDeleted(user.getDeleted());
       userService.addUser(tmpUser);

        return "redirect:/profile/admin";
    }

}




