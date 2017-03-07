package Coop.Sut.userController;

import Coop.Sut.model.ResourceEntiry;
import Coop.Sut.model.SearchSession;
import Coop.Sut.model.Session;
import Coop.Sut.model.User;
import Coop.Sut.repository.UserRepository;
import Coop.Sut.service.RedisService;
import Coop.Sut.service.UserService;
import flexjson.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import flexjson.JSONDeserializer;

import java.util.List;

@RestController
@Transactional(readOnly = false, rollbackFor = Exception.class,
        isolation = Isolation.READ_COMMITTED)
public class UserController {

    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String create(@RequestBody String data) {
        try{
        User user = new JSONDeserializer<User>().use(null, User.class).deserialize(data);
       // userService.insertOfUser(user);

        System.out.print(user.getFirstName());
        System.out.print("insert success");
        }
        catch (Exception ex){
        System.out.print(ex.getStackTrace()+"   "+ex.getMessage());
        }
        return "success : true";
    }


    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody String data) {
        HttpHeaders headers = new HttpHeaders();
        ResourceEntiry resourceEntiry = new ResourceEntiry();

        try {
            User user = new JSONDeserializer<User>().use(null, User.class).deserialize(data);
            User u = userService.findUserByEmailandPassword(user.getEmail(), user.getPassword());
            System.out.print(u.getFirstName());
            if(u==null) {
                throw new RuntimeException();
            }
            resourceEntiry.setSuccess("true");
            resourceEntiry.setDesc("login success");

            return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(resourceEntiry), headers, HttpStatus.OK);

        } catch (Exception ex) {
            resourceEntiry.setSuccess("false");
            resourceEntiry.setDesc("failed login");
            return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(resourceEntiry), headers, HttpStatus.BAD_REQUEST);
        }


    }

    @RequestMapping(value="/insert", method=RequestMethod.POST)
    public void insert(@RequestBody String data){
        Session s = new JSONDeserializer<Session>().use(null, Session.class).deserialize(data);
        System.out.println(s.getKey());
        System.out.println(s.getHashKey());
        redisService.insert(s);

    }
    @RequestMapping(value="/delete", method=RequestMethod.DELETE)
    public void delete(@RequestBody String data){
        SearchSession s = new JSONDeserializer<SearchSession>().use(null, SearchSession.class).deserialize(data);
        System.out.println(s.getKey());
        System.out.println(s.getHashkey());
        redisService.delete(s);
    }

    @RequestMapping(value="/register2", method= RequestMethod.POST)
    public String create2(@RequestParam(value="id") String data){
        System.out.print(data);
        return "Create";
    }

}
