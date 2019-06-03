package com.example.murilomarcineiro.stalker.data;

import android.os.Environment;

import com.example.murilomarcineiro.stalker.model.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Empacotamento {

    // serialização: gravando o objetos no arquivo binário "nomeArq"
    public static void push(ArrayList<Person> list, String nomeArq) {
        File arq = new File(Environment.getExternalStorageDirectory()+nomeArq);
        try {
            arq.delete();
            arq.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(list);
            objOutput.close();

        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    // desserialização: recuperando os objetos gravados no arquivo binário "nomeArq"
    public static ArrayList<Object> pull(String nomeArq) {
        ArrayList<Object> list = new ArrayList();
        try {
            File arq = new File(Environment.getExternalStorageDirectory()+nomeArq);
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                list = (ArrayList<Object>)objInput.readObject();
                objInput.close();
            }
        } catch(IOException erro1) {
            System.out.printf("Erro: %s", erro1.getMessage());
        } catch(ClassNotFoundException erro2) {
            System.out.printf("Erro: %s", erro2.getMessage());
        }

        return(list);
    }
}
