package homework.homework1;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by кайрат on 20.11.2016.
 */
public class ConsolePhoneBook {
    public static void main(String[] args) throws IOException {

        PhoneBook pb = new PhoneBook();

        pb.recoveryBook();

        String name;
        String email;

        System.out.println("1 - Добавить контакт");
        System.out.println("2 - Показать все");
        System.out.println("3 - Редактировать");
        System.out.println("4 - Выход");

        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String code = "default";//читаем строку с клавиатуры

        while(!code.equals("4")){
            code = bufferedReader.readLine();
            if(code.equals("1")){
                System.out.println("Введите имя");
                name = bufferedReader.readLine();
                System.out.println("Введите адрес электронной почты");
                email = bufferedReader.readLine();
                pb.addUser(name,email);
            }
            if(code.equals("2")){
                pb.showAll();
            }
            if(code.equals("3")){
                System.out.println("Введите имя");
                name = bufferedReader.readLine();
                if(pb.containsUser(name)){
                    System.out.println("Введите новый адрес электронной почты");
                    email = bufferedReader.readLine();
                    pb.editUser(name,email);
                }
                else{
                    System.out.println("Такого контакта не существует");
                }

            }

        }

        if(code.equals("4")){
            System.out.println("Выберите способ сохранения");
            System.out.println("1: Serialize 2: Json");
            code = bufferedReader.readLine();
            if(code.equals("1")){pb.writeToByte();}
            if(code.equals("2")){pb.writeToJson();}

        }
    }}
