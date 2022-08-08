package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")  //이렇게 하면 웹 어플리케이션에서 "hello"라고 들어오면 이 메서드를 호출해줘!
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";    //resources- templates 폴더 밑의 hello를 찾아서 렌더링해라!
        //hello.html을 웹 브라우저에 반환
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);  //name을 모델에 넘겨주고
        return "hello-template";    //그리고 템플릿으로 넘어가. 그 변수 안에 대입 돼
    }
    //  ctrl + p를 통해 parameter 정보를 알 수 있어! 단축키!

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
