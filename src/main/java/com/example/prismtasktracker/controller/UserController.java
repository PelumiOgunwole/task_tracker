package com.example.prismtasktracker.controller;

import com.example.prismtasktracker.model.Task;
import com.example.prismtasktracker.model.User;
import com.example.prismtasktracker.repository.TaskRepository;
import com.example.prismtasktracker.repository.UserRepository;
import com.example.prismtasktracker.service.TaskService;
import com.example.prismtasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/signup_form")
    public String showRegister(Model model){
        User user = new User();
        model.addAttribute("user_reg",user);
        return "user_signup";
    }

    @PostMapping("/user/register")
    public String userRegister(@ModelAttribute("user_reg") User user,Model model,HttpSession session){
        // UserId is the user name in my User entity
        String userId = user.getUserName();
        System.out.println(userId);
//        session.setAttribute("use",userService.userRegister(user));

        System.out.println("User Password: "+user.getPassword());
        System.out.println("User C_Password: "+user.getC_password());
        if (user.getC_password().equals(user.getPassword()) && userService.userRegister(user)!=null)
        {
            model.addAttribute("reg_success","Registration Successful");
            System.out.println("Reg Successful");
            return "redirect:/user/login_form";
        }

        else{
            model.addAttribute("error","Password mismatch or" +
                    " User already exists");
            System.out.println("User Already exists");
            return "user_signup";
        }

//        return "redirect:/user/login_form";
    }

    @GetMapping("/user/login_form")
    public String showLoginForm(Model model){
        model.addAttribute("user_login",new User());
        return "user_login";
    }

    @PostMapping("/user/login")
    public String userLogin(@ModelAttribute("user_login") User user, HttpSession session,Model model){
        String userId = user.getUserName();
        System.out.println(userId);

        Optional<User> userNameFromDb = userService.findUserById(userId);
        // Check if user password input is same with that stored in database
        session.setAttribute("user_object",userNameFromDb);

        if (userNameFromDb.isPresent()){
            session.setAttribute("name_of_user", userNameFromDb.get().getUserName());
            return "redirect:/";
        }
        else{
            model.addAttribute("login_error","Invalid Password or User does not exist");
            return "user_login";


        }
    }

    @GetMapping("/user/logout")
    public String logOut(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session!=null){
            session.invalidate();

        }
        return "redirect:/user/login_form";
    }


    @GetMapping("/")
    public  String home(Model model){
        model.addAttribute("task",new Task());

        return "home";
    }


    @PostMapping("/user/add_task")
    public String addTask(@ModelAttribute("task") Task task,HttpSession session, Model model){
//        User user1= new User();
        Optional<User> user = (Optional<User>) session.getAttribute("user_object");
        System.out.println("Current User: "+user);
        if(user!=null) {

            taskService.addTask(task, user.get().getUserName());
            model.addAttribute("task_success", "Task created successfully");
            return "redirect:/";
        }
        else {
            return "redirect:/user/login_form";
        }

    }

    @GetMapping("/user/show_all_tasks")
    public String showAllTasks(HttpSession session){
        Optional<User> user = (Optional<User>) session.getAttribute("user_object");
        if(user!=null){
        List<Task> listOfTasks = taskService.showAllTasks();
        session.setAttribute("list_of_tasks",listOfTasks);
        return "list_of_tasks";}
        else {
            return "redirect/user/login_form";
        }

    }

    @GetMapping("/user/get_task_by_id/{id}")
    public String getTaskById(@PathVariable("id") Integer id,Model model){
        taskService.getTaskById(id);
        return "get_task_by_id";
    }

    @GetMapping("/user/delete_task/{id}")
    public String deleteTask(@PathVariable("id") Integer id){
        taskService.deleteTask(id);
        return "redirect:/user/show_all_tasks";
    }

    @GetMapping("/user/show_update_form/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id,Model model,HttpSession session){
        Optional<Task> taskById = Optional.of(taskService.getTaskById(id).orElse(new Task()));
        System.out.println(taskById);
        // current_task_for_edit will be pre-populated in this form
        model.addAttribute("task_edit",taskById.get());
//        session.setAttribute("task1",taskById);
//        redirAttrs.addFlashAttribute("task",taskById);
        return "edit_task";
    }


    @GetMapping("/user/update_task/{id}")
    public String updateTask(@PathVariable("id") Integer id){
        taskService.updateTask(id);
        return "redirect:/user/show_all_tasks";
    }

    @GetMapping("/user/pendingTask")
    public String showPendingTask( Model model, HttpSession session){

        Optional<User> user = (Optional<User>) session.getAttribute("user_object");
        if(user!=null){
        List<Task> pendingTask =taskService.viewPendingTasks();

        System.out.println(pendingTask.size());
        model.addAttribute("pending_task",pendingTask);
        return "pending";}
        else{
            return "redirect:/user/login_form";
        }
    }

    @GetMapping("/user/inProgressTask")
    public String showInProgressTask( Model model, HttpSession session){

        Optional<User> user = (Optional<User>) session.getAttribute("user_object");
        if(user!=null){
            List<Task> inProgressTask =taskService.viewInProgressTasks();

            System.out.println(inProgressTask.size());
            model.addAttribute("inProgress_task",inProgressTask);
            return "progress";}
        else{
            return "redirect:/user/login_form";
        }
    }

    @GetMapping("/user/doneTask")
    public String showDoneTask( Model model, HttpSession session){

        Optional<User> user = (Optional<User>) session.getAttribute("user_object");
        if(user!=null){
            List<Task> doneTask =taskService.viewDoneTasks();

            System.out.println(doneTask.size());
            model.addAttribute("done_task",doneTask);
            return "done";}
        else{
            return "redirect:/user/login_form";
        }
    }


}
