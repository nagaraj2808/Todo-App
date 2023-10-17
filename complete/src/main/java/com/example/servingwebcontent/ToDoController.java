package com.example.servingwebcontent;
import mongo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;
@Controller
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/todo")
    public String todo(Model model){
        String username = User.username;
        UserItem userItem = userRepository.findUserByUsername(username);
        List<ToDo> list =  toDoRepository.toDoList(username);
        List<String> list_ = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list_.add(list.get(i).getWhatToDo());
        }
        model.addAttribute("Name",userItem.getName()+" your To DO List");
        model.addAttribute("todos", list_);
        return "todo";
    }
    @GetMapping( "/createTaskHandle")
    public ModelAndView createTaskHandle(@RequestParam("task") String task){
        String username = User.username;
        List<ToDo>task_ = toDoRepository.toDoList(username);
            String id = Long.toString(toDoRepository.count());
            ToDo toDo = new ToDo(id,username,task);
            toDoRepository.save(toDo);
        return new ModelAndView("redirect:/todo");
    }

    @GetMapping("/createTask")
    public String createTask(){
        return "createTask";
    }
}
