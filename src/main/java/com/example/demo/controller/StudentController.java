//package com.example.demo.controller;
//
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/students")
//public class StudentController {
//
//    @GetMapping("/{id}")
//    public String getStudentById(@PathVariable int id){
//        return "apne student id"+id+"manga";
//    }
//
//    @GetMapping("/shoes")
//    public String getShoes(@RequestParam String color,@RequestParam int size){
//        return "apko color"+color +"chahiye and size "+size;
//    }
//
//    @PostMapping("/shoes")
//    public boolean createshoes(@PathVariable shoes myshoe ){
//        return true;
//    }
//
//}
