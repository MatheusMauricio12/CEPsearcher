import addressearcher.Address;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CEP Search Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Opções");
        menu.setForeground(Color.WHITE);
        menu.setBackground(Color.DARK_GRAY);
        menu.setFont(new Font("Arial", Font.BOLD, 14));

        JMenuItem searchItem = new JMenuItem("Busca por CEP");
        searchItem.setFont(new Font("Arial", Font.PLAIN, 13));
        searchItem.setBackground(Color.LIGHT_GRAY);
        searchItem.setForeground(Color.BLACK);

        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.setFont(new Font("Arial", Font.PLAIN, 13));
        exitItem.setBackground(Color.LIGHT_GRAY);
        exitItem.setForeground(Color.BLACK);

        menu.add(searchItem);
        menu.add(exitItem);

        menuBar.add(menu);
        menuBar.setBackground(Color.BLACK);
        menuBar.setForeground(Color.WHITE);

        frame.setJMenuBar(menuBar);

        Scanner sc = new Scanner(System.in);
        String search = "";

        List<Address> adresses = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        searchItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String answer = JOptionPane.showInputDialog("Insira o cep: ");
                if(answer != null && !answer.isEmpty()){
                    String address = "https://viacep.com.br/ws/" + answer + "/json/";
                    try {
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(address))
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());
                        String json = response.body();

                        Address myAdress = gson.fromJson(json, Address.class);

                        JOptionPane.showMessageDialog(frame, "Informações sobre o CEP:\n" + myAdress);

                        JOptionPane.showMessageDialog(frame, "O CEP foi adicionado " +
                                "à sua lista json.");

                        adresses.add(myAdress);

                        FileWriter writejson = new FileWriter("adresses.json");
                        writejson.write(gson.toJson(adresses));
                        writejson.close();


                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(frame,"Inserção invalida! Tente novamente.");
                    }
                }
            }
            });
        exitItem.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}

