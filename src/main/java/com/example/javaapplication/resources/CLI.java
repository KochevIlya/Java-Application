package com.example.javaapplication.resources;

import com.example.javaapplication.*;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CLI {
    public void activate(){
        char ch = 0;
        do {
            {

                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.println("Выберите версию программы в соотвествии с форматом входных файлов\n Введите 2, если задан конкретный формат входных данных в файле\n Введите 1 иначе : ");
                String answ;
                try {
                    answ = br.readLine();
                } catch (IOException e) {
                    System.out.println("Вы ввели неверное значение( попробуйте ещё раз");
                    ch = '1';
                    continue;
                }
                System.out.println("Введите имя входного файла, например(input.txt): ");
                String readerName;
                String writerName;
                Result result = new Result();

                try {
                    readerName = br.readLine();
                } catch (IOException e) {
                    System.out.println("Вы ввели неверное имя файла(, попробуйте заново");
                    ch = '1';
                    continue;
                }
                System.out.println("Введите имя выходного файла, например(output.txt): ");
                try {
                    writerName = br.readLine();
                } catch (IOException e) {
                    System.out.println("Вы ввели неверное имя файла(, попробуйте заново");
                    ch = '1';
                    continue;
                }
                try {
                    Decider decider = new Decider();
                    decider.makeDecision(readerName, writerName, result);
                } catch (Exception e) {
                    System.exit(0);
                }
                Reader reader = new Reader(readerName);
                try {
                    if (Objects.equals(answ, "1")) {
                        reader.read(result);
                    } else {
                        reader.readV2(result);
                    }
                } catch (Exception e) {
                    ch = '1';
                    continue;
                }

                Finder finder = new Finder();
                if (Objects.equals(answ, "1")) {
                    finder.find(result);
                } else {
                    finder.findV2(result, readerName);
                }
                System.out.println("Введите 1, если хотите, чтобы подсчёт выражений производился API калькулятором: ");
                try {
                    String temp = br.readLine();
                    Calculator calculator;
                    if (Objects.equals(temp, "1"))
                        calculator = new Calculator(false);
                    else
                        calculator = new Calculator(true);
                    try {
                        calculator.calculate(result);
                    } catch (Exception e) {
                        System.exit(0);
                    }
                } catch (IOException e) {
                    System.out.println("Вы ввели некорректный символ(, попробуйте заново");
                    ch = '1';
                    continue;
                }

                Replacer replacer = new Replacer();

                if (Objects.equals(answ, "1")) {
                    replacer.replace(result);
                } else {
                    try {
                        replacer.replaceV2(result, readerName);
                    } catch (Exception e) {
                        System.out.println("Не удалось заменить выражения на результаты(\n Попробуйте ещё раз!");
                        ch = '1';
                        continue;
                    }
                }

                System.out.println("Введите 1, если хотите, чтобы ваши выходные данные были зашифрованы: ");
                boolean isEnc = false;
                try {
                    String temp = br.readLine();
                    if (Objects.equals(temp, "1")) {
                        result.setShouldEncrypt(true);
                        result.setFirstEncrypt(true);
                        isEnc = true;
                    }
                } catch (IOException e) {
                    System.out.println("Вы ввели некорректный символ(, попробуйте заново");
                    ch = '1';
                    continue;
                }

                System.out.println("Введите 1, если хотите, чтобы ваши выходные данные были заархивированы: ");
                boolean isArch = false;
                try {
                    String temp = br.readLine();
                    if (Objects.equals(temp, "1")) {
                        result.setShouldArchive(true);
                        isArch = true;
                    }
                } catch (IOException e) {
                    System.out.println("Вы ввели некорректный символ(, попробуйте заново");
                    ch = '1';
                    continue;
                }

                if (isEnc && isArch) {
                    System.out.println("Введите 1, если хотите, чтобы сначала данные были заархивированы, а затем зашифрованы: ");
                    try {
                        String temp = br.readLine();
                        if (Objects.equals(temp, "1")) {
                            result.setFirstEncrypt(false);
                        }
                    } catch (IOException e) {
                        System.out.println("Вы ввели некорректный символ(, попробуйте заново");
                        ch = '1';
                        continue;
                    }
                }


                Writer writer = new Writer(writerName);
                writer.write(result);
                System.out.println("Всё готово! проверьте выходной файл;)");
                System.out.println("Введите 1, если хотите продолжить: ");
                try {
                    ch = (char) br.read();
                } catch (IOException e) {
                    System.out.println("Вы ввели некорректный символ(");
                    System.exit(0);
                }
            }
        }
        while(ch == '1');
    }
}
