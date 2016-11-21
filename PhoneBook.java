package homework.homework1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Структура хранения пользователей выбрана HashMap.
 */
public class PhoneBook {
    HashMap<String,String> map = new HashMap<String,String>();

    public static void main(String[] args) {

    }
    public void addUser(String name, String email){  map.put(name,email);}
    public void showAll(){System.out.println(map);}
    public boolean editUser(String name, String email){
        if(map.containsKey(name)){
            map.remove(name);
            map.put(name,email);
            return true;
        }
        return false;
    }
    public void writeToByte() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream("C://D//byte.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void writeToJson(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();



        String usersJson = gson.toJson(map);

        try
        {
            FileOutputStream fos=new FileOutputStream("C://D//note.txt");
            // перевод строки в байты
            byte[] buffer = usersJson.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public void recoveryBook(){
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        HashMap<String,String> map1 =readByte();
        HashMap<String,String> map2 =readJson();
        String code ="";

        Iterator it1 = map1.entrySet().iterator();
        Iterator it2 = map2.entrySet().iterator();
        while (it1.hasNext())
        {
            Map.Entry<String, String> entry1 = (Map.Entry<String, String>) it1.next();
            while (it2.hasNext())
            {
                Map.Entry<String, String> entry2 = (Map.Entry<String, String>) it2.next();

                if(entry1.getKey().equals(entry2.getKey())) {
                    System.out.println("Найден контакт c одинаковым именем. Какой удалить?");
                    System.out.println("1:"+entry1.getValue()+" 2:"+entry2.getValue());
                    try {
                        code = bufferedReader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(code.equals("1")){
                        it1.remove();
                    }
                    if(code.equals("2")){
                        it2.remove();
                    }
                    System.out.println(map1);
                    System.out.println(map2);
                }


            }
        }
    }
    public boolean containsUser(String name){
        if(map.containsKey(name)){return true;}
        return false;
    }
    private HashMap<String,String> readByte(){
        ObjectInputStream ois = null;
        HashMap<String,String> u = null;
        try {
            FileInputStream fis = new FileInputStream("C://D//byte.txt");
            ois = new ObjectInputStream(fis);
            u = (HashMap<String,String>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return u;
    }
    private HashMap<String,String> readJson(){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String name1="";
        try
        {
            FileInputStream fin=new FileInputStream("C://D//note.txt");

            int i=-1;
            while((i=fin.read())!=-1){

                name1 +=(char)i;
            }

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        HashMap<String,String> parsedUsers = gson.fromJson(name1,
                new TypeToken<HashMap<String,String>>() {}.getType());
        return parsedUsers;
    }
}
