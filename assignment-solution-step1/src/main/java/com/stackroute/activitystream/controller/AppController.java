package com.stackroute.activitystream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.activitystream.config.HibernateUtil;
import com.stackroute.activitystream.model.Message;

/*Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller*/

@Controller
public class AppController {

	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement two functionalities. They are as following:
	 * 
	 * 1. display the list of existing messages from the database. Each message
	 *    should contain senderName, message, and timestamp 
	 * 2. send a message which should contain the senderName, message, and timestamp.
	 * 
	 */
	/*@RequestMapping("/")
	public String indexPage(){
		return "index";
	}*/
	
	/*Define a handler method to read the existing messages from the database and add it to
	the ModelMap which is an implementation of Map for use when building model data for use 
	with views. it should map to the default URL i.e. "/" */
	
	@RequestMapping(value={"/","/index"})
	public String home(Model model)
	{
		model.addAttribute("message",new Message());
		model.addAttribute("messageList",new HibernateUtil().getListOfMessages());
		return "index";
	}
	
	
	/*Define a handler method which will read the senderName and message from request parameters and
	 * save the message in message table in database. Please note that the timestamp should always
	 * be auto populated with system time and should not be accepted from the user. Also, after 
	 * saving the message, it should show the same along with existing messages. Hence, reading 
	 * messages has to be done here again and the retrieved messages object should be sent back to the 
	 * view using ModelMap
	 * This handler method should map to the URL "/sendMessage". 
	*/
	@RequestMapping(value="/sendMessage" , method=RequestMethod.POST)
	public String sendMessage(@RequestParam("senderName")String senderName,@RequestParam("message")String message,Model model)
	{
		/*if(result.hasErrors())
		{
			model.addAttribute("messageList",new HibernateUtil().getListOfMessages());
			return "index";
		 }
		else
		{
		HibernateUtil hibernateUtil=new HibernateUtil();
		if(hibernateUtil.sendMessage(message))
			System.out.println("Data inserted");
			return "redirect:/index";
		}	*/
		
		
		Message message1=new Message();
		message1.setMessage(message);
		message1.setSenderName(senderName);
		HibernateUtil hibernateUtil=new HibernateUtil();
		if(hibernateUtil.sendMessage(message1))
		System.out.println("Data inserted");
			return "redirect:/index";
		
	}

}