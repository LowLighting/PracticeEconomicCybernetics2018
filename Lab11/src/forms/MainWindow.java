package forms;

import Lab8.Buffer;
import Lab8.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class MainWindow extends JFrame {
    private JPanel formPanel;
    private JTextField NumberStock;
    private JTextField Code;
    private JTextField NameProduct;
    private JTextField ShelfLife;
    private JPanel listPanel;
    private JPanel dataPanel;
    private JTextField NumberItems;
    private JPanel JListPanel;
    private JList listView;
    private JButton submitButton;
    private JButton cleanButton;
    private JButton findUp;
    private JButton findLow;
    private JButton remove;
    private JTextField Index;
    private JTextField status;
    private JToolBar statusBar;
    private JTextField Price;


    MainWindow() throws ParseException {
        JFrame self = this;
        this.getContentPane().add(formPanel);
        DefaultListModel<Stock> listModel = new DefaultListModel<>();
        listView.setModel(listModel);
        ArrayList<Stock> magazine = new ArrayList<>();
        createArrayList(magazine);
        Map<Object, Long> map = Buffer.write(magazine);

        for (Stock arrayList : magazine) {
            listModel.addElement(arrayList);
            status.setText("Data added");
        }

        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numberstockText =Integer.parseInt(NumberStock.getText());
                double CodeText =Double.parseDouble(Code.getText());
                int shelflifeText=Integer.parseInt(ShelfLife.getText());
                int numberofitemsText=Integer.parseInt(NumberItems.getText());
                double priceText =Double.parseDouble(Price.getText());
                String NameProductText = NameProduct.getText();
                listModel.addElement(new Stock(numberstockText,CodeText,NameProductText,shelflifeText,numberofitemsText,priceText));
                status.setText("Data added by input");

            }

        });
        cleanButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
            }
        });
        findLow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Stock record = Buffer.moveLow(magazine.get(index).getCode(), map);
                listModel.addElement(record);
                status.setText("Find Low!");

            }
        });
        findUp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Stock record = Buffer.moveUp(magazine.get(index).getCode(), map);
                listModel.addElement(record);
                status.setText("Find top!");
            }
        });
        remove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Buffer.removeByIndex(magazine.get(index).getCode(),map);

                for (Map.Entry<Object, Long> entry : map.entrySet()) {
                    Stock record =  Buffer.read(entry.getKey(), map);
                    if (record != null) {
                        listModel.addElement(Buffer.read(entry.getKey(), map));
                    }
                }
                status.setText("Remome by index!");
            }
        });
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        load.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showOpenDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File file = fileChooser.getSelectedFile();

                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    ArrayList loadedInvoices = (ArrayList) ois.readObject();
                    ois.close();
                    fis.close();

                    listModel.clear();
                    for (Object record : loadedInvoices) {
                        listModel.addElement((Stock) record);
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                status.setText("File load!");
            }
        });

        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showSaveDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File file = fileChooser.getSelectedFile();


                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    ArrayList<Stock> records = new ArrayList<>();

                    for (int i = 0; i < listModel.size(); ++i) {
                        records.add(listModel.get(i));
                    }

                    oos.writeObject(records);
                    oos.close();
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.setText("File save!");
            }
        });
    }

    private static void createArrayList(ArrayList<Stock> magazine) {
        Stock firstStock = new Stock(125, 1252, "Beef", 6, 120, 20);
        Stock secondStock = new Stock(125,1526,"Milk",7,190,3.4);
        Stock thirdStock = new Stock(125,156,"Cheese",18,100,12.6);

        magazine.add(firstStock);
        magazine.add(secondStock);
        magazine.add(thirdStock);
    }

}
