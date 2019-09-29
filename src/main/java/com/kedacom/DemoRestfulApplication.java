package com.kedacom;

import com.kedacom.domain.User;
import com.kedacom.service.Impl.UserServiceImpl;
import com.kedacom.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class DemoRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestfulApplication.class, args);
	}

}
