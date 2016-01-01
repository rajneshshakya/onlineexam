package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Exam;
import models.QuestionPaper1;
import models.Questions;
import models.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;

public class Application extends Controller {

    public Result index() {
        return ok(views.html.index.render("Your new application is ready."));
    }
    public Result reg()
    {
    	return ok(views.html.registration.render(""));
    }
    public Result login()
    {
    	return ok(views.html.signin.render(""));
    }
    
    public Result addQ()
    {
    	return ok(views.html.add.render(""));
    }
    
    public Result start1()
    {
    	
    	
    	
    	if(session("count")==null)
    	{
    		session("count","0");
    	}
    	QuestionPaper1 q=new Model.Finder<>(Integer.class,QuestionPaper1.class).byId(1);
       	List <Questions> list=q.getQuestions();
       	String count=session("count");
       	int count1=Integer.parseInt(count);
       	if(count1>=list.size())
       	{
       		count1=0;
       		session("count","0");
       		
       	}
       	
       	Questions que=list.get(count1);
    	
    	return ok(views.html.read1.render(que));
    }

   public Result submit()
   {
	   Questions que1=Form.form(Questions.class).bindFromRequest().get();
	   	System.out.println(que1.getRightOption());
		String count=session("count");
	   	int count1=Integer.parseInt(count);
	   	QuestionPaper1 q=new Model.Finder<>(Integer.class,QuestionPaper1.class).byId(1);
	   	List <Questions> list=q.getQuestions();
	   	Questions que3=list.get(count1);
	   	count1=count1+1;
	   	session("count",count1+"");
	   	
	   
	   	
	   	
	   	if(que3.getRightOption().equals(que1.getRightOption()))
	   	{
	   		if(session("ans")==null)
	   		{
	   			session("ans","0");
	   		}
	   		String ans=session("ans");
	   		int ans1=Integer.parseInt(ans);
	   		ans1++;
	   		session("ans",ans1+"");
	   		System.out.println(ans1);
	   		
	   	}
	   String ans="you got "+session("ans")+" marks";
	   session("ans","0");
	   session("count","0");
	   
	   return ok(views.html.welcome.render(ans));
	   
   }
   
   
   public Result start()
   {
	 
	   
    Questions que1=Form.form(Questions.class).bindFromRequest().get();
    String value=Form.form().bindFromRequest().get("submit");
    System.out.println("submit"+value);
   	System.out.println(que1.getRightOption());
   	
   	
	String count=session("count");
   	int count1=Integer.parseInt(count);
   	QuestionPaper1 q=new Model.Finder<>(Integer.class,QuestionPaper1.class).byId(1);
   	List <Questions> list=q.getQuestions();
   	Questions que3=list.get(count1);
   	count1=count1+1;
   	session("count",count1+"");
   	
   
   	
   	if(que1.getRightOption()!=null)
   	{
   	if(que3.getRightOption().trim().equals(que1.getRightOption().trim()))
   	{
   		if(session("ans")==null)
   		{
   			session("ans","0");
   		}
   		String ans=session("ans");
   		int ans1=Integer.parseInt(ans);
   		ans1++;
   		session("ans",ans1+"");
   		System.out.println(ans1);
   		
   	}
   	}
   	
	if(count1>=list.size())
   	{
   	 String ans="you got "+session("ans")+" marks";
	   session("ans","0");
	   session("count","0");
	   return ok(views.html.welcome.render(ans));
   	}
   if(value.equals("submit"))
   {

	   	 String ans="you got "+session("ans")+" marks";
		   session("ans","0");
		   session("count","0");
		   return ok(views.html.welcome.render(ans)); 
   }
	
   	
   	Questions que=list.get(count1);
   	
   
	  return ok(views.html.read1.render(que)); 
	   
	   
	   
   }
    
    public Result register()
    {
    	Student s=Form.form(Student.class).bindFromRequest().get();
    	
    	
    	Student s1=new Student();
    	s1.setEmail(s.getEmail());
    	s1.setPassword1(s.getPassword1());
    	s1.setUser_name(s.getUser_name());
    	Exam exam=new Model.Finder<>(Integer.class, Exam.class).byId(1);
    	System.out.println(exam.getId());
    	ArrayList<Student> al=new ArrayList<Student>();
    	al.add(s1);
    	exam.setStudents(al);
        exam.save();
    	return ok("save");
    }
    public Result loginStu()
    {
    	Student s=Form.form(Student.class).bindFromRequest().get();
    	ExpressionList<Student> elist=new Model.Finder<>(Integer.class, Student.class).where().eq("email", s.getEmail());
    	List <Student> list=elist.findList();
    	Iterator <Student>itr=list.iterator();
    	Student s1;
    	int i=0;
    	while(itr.hasNext())
    	{
    		s1=itr.next();
    		if(s1.getPassword1().equals(s.getPassword1()))
    		{
    			i++;
    			return ok(views.html.welcome.render(""));
    		}
    	}
    	if(i==0)
    	{
    		return ok("user name or password wrong");
    	}
    	return ok();
    }
    
    public Result add()
    {
     Questions que=Form.form(Questions.class).bindFromRequest().get();
     QuestionPaper1 que1=new Model.Finder<>(Integer.class, QuestionPaper1.class).byId(1);
     ArrayList <Questions> list=new ArrayList<Questions>();
     list.add(que);
     que1.setQuestions(list);
     que1.save();
     return ok(views.html.add.render(""));
    	
    }
    
}
