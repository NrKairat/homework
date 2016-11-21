package homework.homework1;

import java.io.*;


/**
 * Created by кайрат on 20.11.2016.
 * Консольная версия телефонной книги. Программа осуществляет добаление, редактирование, вывод на экран пользователей и
 * сохранения их в 2-х видах: 1 Сериализация объекта в байты 2 Перевод объекта в Json, затем его запись в файл.
 * При воссстановлении программа считывает оба файла, перефодит их в hashmap-ы и сравнивает на уникальность имен, при
 * совпадении имен пользователю предлагается удалить один из контактов.
 */
public class ConsolePhoneBook {
    public static void main(String[] args) throws IOException {

        PhoneBook pb = new PhoneBook();//Создаем объект телефонной книги

        pb.recoveryBook();// Проверяем 2 файла на уникальность имен

        String name;
        String email;
        // Вывод меню на консоль
        System.out.println("1 - Добавить контакт");
        System.out.println("2 - Показать все");
        System.out.println("3 - Редактировать");
        System.out.println("4 - Выход");

        BufferedReader bufferedReader = pb.getBufferedReader();
        String code = "default";//читаем строку с клавиатуры

        while(!code.equals("4")){ //Взаимодействие с пользователем, запрос нажатий клавиш
            code = bufferedReader.readLine();//Чтение команды пользователя
            if(code.equals("1")){
                System.out.println("Введите имя");
                name = bufferedReader.readLine();
                System.out.println("Введите адрес электронной почты");
                email = bufferedReader.readLine();
                pb.addUser(name,email);// Добавление пользователя
            }
            if(code.equals("2")){
                pb.showAll(); //Вывод всех пользователей на экран
            }
            if(code.equals("3")){ //Редактирование пользователя
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
            if(code.equals("1")){pb.writeToByte();}//Сохранения коллекции в байты
            if(code.equals("2")){pb.writeToJson();}//Сохранения коллекции в Json

        }
    }}
