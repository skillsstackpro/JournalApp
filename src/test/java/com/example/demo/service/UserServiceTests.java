package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindByuserName(){
        System.out.println("total users "+userRepository.findAll().size());
        User user =userRepository.findByUserName("ram");
        System.out.println(user);
        assertNotNull(user);
        assertNotNull(userRepository.findByUserName("ram")); //assertnull
        // assertTrue(user.getJournalEntries().isEmpty()); ,assertfalse
        // assertThrows(Exception.class, () -> executable)
    }
    @BeforeEach //her ek test se phly ,aftereach her ek test k bd
    //beforeall mtlb tests start hone se phly 1 bar,afterall  mtlb tests khtm hone ke bd 1 bar. all wale must be static
    void setUp(){
        userRepository.deleteAll();
    }

    @ParameterizedTest
//    @CsvSource({}) for multiple arguments like a,b,c
//    @CsvFileSource same but morethan50+ data and in.csv format
//    @EnumSource({}) for all values like,roles admin,users
    @ValueSource(strings ={ //for single argument
            "ram",
            "shyam",
            "rohan"
    })
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testsSaveNewUser(User user){
        assertNotNull(userService.saveNewUser(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
        //assertNotEquals();
    }
}
