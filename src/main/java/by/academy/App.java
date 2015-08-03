package by.academy;

import java.util.List;
import java.util.Scanner;

import by.academy.dao.GenericDao;
import by.academy.dao.UserDao;
import by.academy.dao.impl.RoleDaoImpl;
import by.academy.dao.impl.TourDaoImpl;
import by.academy.dao.impl.UserDaoImpl;
import by.academy.entity.Role;
import by.academy.entity.User;
import by.academy.entity.Tour;
import static by.academy.util.PropertiesManager.MESSAGE;

public class App 
{
    public static void main( String[] args )
    {
    	Scanner in = new Scanner(System.in);
    	int choice;
    	do{
    		printMenu();
    		choice = in.nextInt();
    		switch(choice){
    		case 1: addUser(in);
    				break;
    		case 2: findUser(in);
    				break;
    		case 3: findAllUsers();
    				break;
    		case 4: addUserRole(in);
    				break;
    		case 5: findUserRoles(in);
    				break;
    		case 6: addTour(in);
    				break;
    		case 7: findTour(in);
    				break;
    		case 8: findAllTours();
    				break;
    		case 9: addRole(in);
    				break;
    		case 10: findRole(in);
    				break;
    		case 11: findAllRoles();
    				break;
    		default: continue;
    		}
    		System.out.println(MESSAGE.getProperty("check"));
    		while(true){
    			if(in.next() != null){
    				break;
    			}
    		}
    	} while(choice != 0);
		in.close();
    }
    
    private static void printMenu(){
    	System.out.println(MESSAGE.getProperty("item.add.user"));
    	System.out.println(MESSAGE.getProperty("item.find.user"));
    	System.out.println(MESSAGE.getProperty("item.find.all.users"));
    	System.out.println(MESSAGE.getProperty("item.add.user.role"));
    	System.out.println(MESSAGE.getProperty("item.find.user.roles"));
    	System.out.println(MESSAGE.getProperty("item.add.tour"));
    	System.out.println(MESSAGE.getProperty("item.find.tour"));
    	System.out.println(MESSAGE.getProperty("item.find.all.tours"));
    	System.out.println(MESSAGE.getProperty("item.add.role"));
    	System.out.println(MESSAGE.getProperty("item.find.role"));
    	System.out.println(MESSAGE.getProperty("item.find.all.roles"));
    	System.out.println(MESSAGE.getProperty("exit"));
    	System.out.println(MESSAGE.getProperty("title"));
    }
    
    private static void addUser(Scanner scanner){
    	User user = new User();
    	System.out.println(MESSAGE.getProperty("input.user.login"));
    	String login = scanner.next();
    	System.out.println(MESSAGE.getProperty("input.user.pass"));
    	String pass = scanner.next();
    	System.out.println(MESSAGE.getProperty("input.role.id"));
    	int roleId = scanner.nextInt();
        user.setLogin(login);
        user.setPass(pass);
        GenericDao<User> userDao = UserDaoImpl.getInstance();
        userDao.create(user);
        user = userDao.read(user);
        ((UserDao)userDao).addRole(user.getId(), roleId);
    }
    
    private static void findUser(Scanner scanner){
    	User user = new User();
    	System.out.println(MESSAGE.getProperty("input.user.login"));
    	String login = scanner.next();
    	user.setLogin(login);
    	System.out.println(MESSAGE.getProperty("input.user.pass"));
    	String pass = scanner.next();
    	user.setPass(pass);
    	GenericDao<User> userDao = UserDaoImpl.getInstance();
        User newUser = userDao.read(user);
        List<Role> roles = ((UserDao) userDao).findRoles(newUser.getId());
        newUser.setRoles(roles);
        System.out.println(newUser);
    }
    
    private static void findAllUsers(){
    	GenericDao<User> userDao = UserDaoImpl.getInstance();
    	List<User> users = userDao.readAll();
    	for(User user: users){
    		List<Role> roles = ((UserDao) userDao).findRoles(user.getId());
            user.setRoles(roles);
        	System.out.println(user);
        }
    }
    
    private static void addUserRole(Scanner scanner){
    	System.out.println(MESSAGE.getProperty("input.user.id"));
    	int userId = scanner.nextInt();
    	System.out.println(MESSAGE.getProperty("input.role.id"));
    	int roleId = scanner.nextInt();
    	UserDao userDao = UserDaoImpl.getInstance();
    	userDao.addRole(userId, roleId);
    }
    
    private static void findUserRoles(Scanner scanner){
    	System.out.println(MESSAGE.getProperty("input.user.id"));
    	int userId = scanner.nextInt();
    	UserDao userDao = UserDaoImpl.getInstance();
    	for(Role role : userDao.findRoles(userId)){
    		System.out.println(role);
    	}
    }
    
    private static void addTour(Scanner scanner){
    	Tour tour = new Tour();
    	System.out.println(MESSAGE.getProperty("input.tour.heading"));
    	String heading = scanner.next();
    	System.out.println(MESSAGE.getProperty("input.tour.text"));
    	String text = scanner.next();
    	System.out.println(MESSAGE.getProperty("input.tour.duration"));
    	int duration = scanner.nextInt();
    	System.out.println(MESSAGE.getProperty("input.tour.price"));
    	int price = scanner.nextInt();
    	tour.setHeading(heading);
    	tour.setText(text);
    	tour.setDuration(duration);
    	tour.setPrice(price);
        GenericDao<Tour> tourDao = TourDaoImpl.getInstance();
        tourDao.create(tour);
    }
    
    private static void findTour(Scanner scanner){
    	Tour tour = new Tour();
    	System.out.println(MESSAGE.getProperty("input.tour.id"));
    	int id = scanner.nextInt();
    	tour.setId(id);
    	GenericDao<Tour> tourDao = TourDaoImpl.getInstance();
        Tour newTour = tourDao.read(tour);
        System.out.println(newTour);
    }
    
    private static void findAllTours(){
    	GenericDao<Tour> tourDao = TourDaoImpl.getInstance();
    	List<Tour> tours = tourDao.readAll();
        for(Tour tour: tours){
        	System.out.println(tour);
        }
    }
    
    private static void addRole(Scanner scanner){
    	Role role = new Role();
    	System.out.println(MESSAGE.getProperty("input.role.name"));
    	String name = scanner.next();
    	role.setName(name);
        GenericDao<Role> roleDao = RoleDaoImpl.getInstance();
        roleDao.create(role);
    }
    
    private static void findRole(Scanner scanner){
    	Role role = new Role();
    	System.out.println(MESSAGE.getProperty("input.role.id"));
    	int id = scanner.nextInt();
    	role.setId(id);
    	GenericDao<Role> roleDao = RoleDaoImpl.getInstance();
        Role newRole = roleDao.read(role);
        System.out.println(newRole);
    }
    
    private static void findAllRoles(){
    	GenericDao<Role> roleDao = RoleDaoImpl.getInstance();
    	List<Role> roles = roleDao.readAll();
        for(Role role: roles){
        	System.out.println(role);
        }
    }
}
