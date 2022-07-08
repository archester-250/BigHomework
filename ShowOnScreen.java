import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ShowOnScreen {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Dimension frameSize = new Dimension(800, 600);
        Dimension dialogSize = new Dimension(400, 300);
        JFrame jFrame = new JFrame("购物车");
        jFrame.setSize(frameSize);
        jFrame.setLocation(new Point((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2));
        Container container = jFrame.getContentPane();
        container.setLayout(new BorderLayout());
        Label title = new Label("结算页面", Label.CENTER);
        title.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
        container.add(title, BorderLayout.NORTH);
        JButton submit = new JButton("提交订单");
        container.add(submit, BorderLayout.SOUTH);

        ArrayList items = new ArrayList<SaleLineItem>(0);
        JMenu jMenu_options = new JMenu("选项");
        JMenuItem jMenuItem_add = new JMenuItem("添加书本至购物车...");
        JPanel details = new JPanel(new GridLayout(items.size(), 3, 0, 20));
        JScrollPane jScrollPane = new JScrollPane(details);
        ArrayList copies = new ArrayList<JTextField>(0);
        jMenuItem_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJDialog_add jDialog = MyJDialog_add.getInstance_jDialog();
                jDialog.setTitle("添加至购物车");
                jDialog.setSize(dialogSize);
                jDialog.setLocation(new Point((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2));
                Container container_1 = jDialog.getContentPane();
                container_1.removeAll();
                container_1.setLayout(new BorderLayout());
                Label title = new Label("添加至购物车", Label.CENTER);
                title.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
                container_1.add(title, BorderLayout.NORTH);
                JButton add = new JButton("添加");
                container_1.add(add, BorderLayout.SOUTH);

                JPanel jPanel = new JPanel(new GridLayout(4, 2, 0, 25));
                Label[] labels = new Label[]{new Label("请输入ISBN号："), new Label("请输入价格"),
                        new Label("请输入书名："), new Label("请输入类型：")};
                JTextField[] textFields = new JTextField[]{new JTextField(), new JTextField(),
                        new JTextField(), new JTextField()};
                for(int i = 0; i < 4; i++)
                {
                    labels[i].setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                    textFields[i].setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                    textFields[i].setHorizontalAlignment(JTextField.CENTER);
                    textFields[i].setText("");
                    jPanel.add(labels[i]);
                    jPanel.add(textFields[i]);
                }
                container_1.add(jPanel, BorderLayout.CENTER);

                JLabel instruction = new JLabel("<html>说明：<br>0-教材类图书<br>1-连环画类<br>2-非教材类的计算机图书<br>3-其他</html>");
                instruction.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 10));
                container_1.add(instruction, BorderLayout.EAST);
                jDialog.setVisible(true);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            for (int i = 0; i < 4; i++)
                            {
                                if(textFields[i].getText().isEmpty()) throw new NullPointerException();
                            }
                            for (int i = 0; i < items.size(); i++)
                            {
                                if(textFields[0].getText().equals(((SaleLineItem)items.get(i)).getProdSpec().getIsbn()))
                                    throw new ArrayStoreException();
                            }
                            long ISBN = Long.parseLong(textFields[0].getText());
                            double price = Double.parseDouble(textFields[1].getText());
                            int type = Integer.parseInt(textFields[3].getText());
                            if(type == 0 && price < PricingStrategyFactory.getDiscountPerBook())
                                throw new PriceException();
                            if(type < 0 || type > 3) throw new NumberFormatException();

                            PricingStrategyFactory pricingStrategyFactory = PricingStrategyFactory.getInstance();
                            SaleLineItem item = new SaleLineItem(1,
                                    new ProductSpecification(textFields[0].getText(), price,
                                            textFields[2].getText(), type),
                                    pricingStrategyFactory.getPricingStrategy(type));
                            items.add(item);
                            details.setLayout(new GridLayout(items.size(), 3, 0, 20));

                            String text = "<html>ISBN号：" + ((SaleLineItem)items.get(items.size() - 1)).getProdSpec().getIsbn()
                                    + "<br>原价：" + ((SaleLineItem)items.get(items.size() - 1)).getProdSpec().getPrice()
                                    + "<br>书名：" + ((SaleLineItem)items.get(items.size() - 1)).getProdSpec().getTitle()
                                    + "<br>类型：";
                            switch (((SaleLineItem)items.get(items.size() - 1)).getProdSpec().getType())
                            {
                                case 0:
                                    text += "教材类图书";
                                    break;
                                case 1:
                                    text += "连环画类";
                                    break;
                                case 2:
                                    text += "非教材类的计算机图书";
                                    break;
                                default:
                                    text += "其他类图书";
                            }
                            text += "</html>";
                            JLabel bookDetail = new JLabel(text);
                            bookDetail.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                            JLabel count = new JLabel("数量：");
                            count.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                            JTextField field = new JTextField("1");
                            field.setHorizontalAlignment(JTextField.CENTER);
                            field.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                            copies.add(field);
                            details.add(bookDetail);
                            details.add(count);
                            details.add((JTextField)(copies.get(items.size() - 1)));
                            container.add(jScrollPane, BorderLayout.CENTER);
                            jFrame.setVisible(true);
                            JOptionPane.showMessageDialog(jDialog, "添加成功！", "Success", JOptionPane.OK_OPTION);
                            jDialog.dispose();

                        }catch (NumberFormatException nfe)
                        {
                            JOptionPane.showMessageDialog(jDialog, "输入类型/格式错误！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (NullPointerException npe)
                        {
                            JOptionPane.showMessageDialog(jDialog, "输入不能为空！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (PriceException pe)
                        {
                            JOptionPane.showMessageDialog(jDialog, "定价小于折扣价！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (ArrayStoreException ase)
                        {
                            JOptionPane.showMessageDialog(jDialog, "ISBN号重复添加！", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });
        jMenu_options.add(jMenuItem_add);
        JMenu jMenu_test = new JMenu("测试");
        JMenuItem jMenuItem_test = new JMenuItem("测试用例结果输出");
        jMenu_test.add(jMenuItem_test);
        jMenuItem_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJDialog_test testResult = MyJDialog_test.getInstance_jDialog();
                testResult.setTitle("测试结果");
                testResult.setSize(dialogSize);
                testResult.setLocation(new Point((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2));
                Container container1 = testResult.getContentPane();
                container1.removeAll();
                container1.setLayout(new BorderLayout());
                JLabel jLabel = new JLabel("测试用例的总额为：" + Test.Test());
                jLabel.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
                jLabel.setHorizontalAlignment(JLabel.CENTER);
                container1.add(jLabel, BorderLayout.CENTER);
                JButton jButton = new JButton("生成测试用例购物单");
                container1.add(jButton, BorderLayout.SOUTH);
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            log(items, "（测试）");
                            testResult.dispose();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(testResult, "IO异常！请联系管理员", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                testResult.setVisible(true);
            }
        });

        JMenu jMenu_modify = new JMenu("修改");
        JMenuItem jMenuItem_modify = new JMenuItem("修改降价幅度");
        jMenu_modify.add(jMenuItem_modify);
        jMenuItem_modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyJDialog_modify modify = MyJDialog_modify.getInstance_jDialog();
                modify.setTitle("修改降价幅度");
                modify.setSize(dialogSize);
                modify.setLocation(new Point((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2));
                Container container1 = modify.getContentPane();
                container1.removeAll();
                container1.setLayout(new BorderLayout());
                JPanel jPanel = new JPanel(new GridLayout(2, 2));
                JLabel label_1 = new JLabel("请选择要修改的类型：");
                label_1.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                JComboBox jComboBox = new JComboBox<String>();
                jComboBox.addItem("--请选择--");
                jComboBox.addItem("教材类图书");
                jComboBox.addItem("连环画类");
                jComboBox.addItem("非教材类的计算机图书");
                jComboBox.setSelectedIndex(0);
                jPanel.add(label_1);
                jPanel.add(jComboBox);
                JLabel label_2 = new JLabel("<html>请输入修改后的数值<br>教材类图书：每本书的降价<br>连环画和非教材类的计算机图书：打折百分比</html>");
                label_2.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                JTextField textField = new JTextField();
                textField.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 15));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setText("");
                jPanel.add(label_2);
                jPanel.add(textField);

                container1.add(jPanel, BorderLayout.CENTER);

                JButton confirm = new JButton("确认");
                container1.add(confirm, BorderLayout.SOUTH);
                modify.setVisible(true);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if(textField.getText().isEmpty()) throw new NullPointerException();
                            int index = jComboBox.getSelectedIndex();
                            switch (index)
                            {
                                case 1:
                                    for(int i = 0; i < items.size(); i++)
                                    {
                                        if(Integer.parseInt(textField.getText()) > ((SaleLineItem)items.get(i)).getProdSpec().getPrice())
                                            throw new PriceException();
                                    }
                                    PricingStrategyFactory.setDiscountPerBook(Integer.parseInt(textField.getText()));
                                    break;
                                case 2:
                                    if(Integer.parseInt(textField.getText()) < 0 || Integer.parseInt(textField.getText()) > 100) throw new PercentException();
                                    PricingStrategyFactory.setDiscountPercentage_1(Integer.parseInt(textField.getText()));
                                    break;
                                case 3:
                                    if(Integer.parseInt(textField.getText()) < 0 || Integer.parseInt(textField.getText()) > 100) throw new PercentException();
                                    PricingStrategyFactory.setDiscountPercentage_2(Integer.parseInt(textField.getText()));
                                    break;
                                default:
                                    throw new NullPointerException();
                            }
                            for(int i = 0; i < items.size(); i++)
                            {
                                if(index - 1 == ((SaleLineItem)items.get(i)).getProdSpec().getType())
                                {
                                    PricingStrategyFactory pricingStrategyFactory =
                                            PricingStrategyFactory.getInstance();
                                    ((SaleLineItem)items.get(i)).setStrategy(
                                            pricingStrategyFactory.getPricingStrategy(index - 1));
                                }
                            }
                            JOptionPane.showMessageDialog(modify, "修改成功！", "Success", JOptionPane.OK_OPTION);
                            modify.dispose();
                        }catch (NumberFormatException nfe)
                        {
                            JOptionPane.showMessageDialog(modify, "输入类型错误！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (NullPointerException npe)
                        {
                            JOptionPane.showMessageDialog(modify, "请选择类型或输入数值！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (PercentException pe)
                        {
                            JOptionPane.showMessageDialog(modify, "百分比输入错误！", "Error", JOptionPane.ERROR_MESSAGE);
                        }catch (PriceException pre)
                        {
                            JOptionPane.showMessageDialog(modify, "折扣价大于最低价！", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            }
        });

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(jMenu_options);
        jMenuBar.add(jMenu_test);
        jMenuBar.add(jMenu_modify);
        jFrame.setJMenuBar(jMenuBar);

        submit.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for(int i = 0; i < items.size(); i++)
                    {
                        if(Integer.parseInt(((JTextField)copies.get(i)).getText()) < 0) throw new NumberFormatException();
                        ((SaleLineItem)items.get(i)).setCopies(Integer.parseInt(((JTextField)copies.get(i)).getText()));
                    }
                    Sale sale = new Sale(items);
                    MyJDialog_result result = MyJDialog_result.getInstance_jDialog();
                    result.setTitle("结算");
                    result.setSize(dialogSize);
                    result.setLocation(new Point((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2));
                    Container container1 = result.getContentPane();
                    container1.removeAll();
                    container1.setLayout(new BorderLayout());
                    JLabel jLabel = new JLabel("您此次购物的金额为：" + sale.getTotal());
                    jLabel.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
                    jLabel.setHorizontalAlignment(JLabel.CENTER);
                    container1.add(jLabel, BorderLayout.CENTER);
                    JButton jButton = new JButton("生成本次购物单");
                    container1.add(jButton, BorderLayout.SOUTH);
                    jButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                log(items, "");
                                result.dispose();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(result, "IO异常！请联系管理员", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    result.setVisible(true);
                }catch (NumberFormatException nfe)
                {
                    JOptionPane.showMessageDialog(jFrame, "输入类型错误！", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        container.add(submit, BorderLayout.SOUTH);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        jFrame.setVisible(true);
    }

    public static void log (ArrayList items, String str) throws IOException
    {
        FileWriter fw = new FileWriter("list.txt");
        fw.write("****************************************欢迎光临电子书城！****************************************\n" +
                    "                                                                      购物清单" + str + "\n\n");
        fw.write("==============================================================\n\n");
        fw.write("书名\t\t\t\t数量\t\t\t单价\n\n");

        if(str.isEmpty())
        {
            for(int i = 0; i < items.size(); i++)
            {
                fw.write(((SaleLineItem)items.get(i)).getProdSpec().getTitle() + "\t\t\t"
                        + ((SaleLineItem)items.get(i)).getCopies() + "\t\t\t" +
                        ((SaleLineItem)items.get(i)).getProdSpec().getPrice() + "\n\n");
            }
        }
        else
        {
            double[] test_price = new double[]{18, 34, 58, 30, 20};
            String[] test_title = new String[]{"《UML与模式应用》", "《Java与模式》", "《HeadFirst 设计模式》", "《爱丽丝历险记》", "《煲汤大全》"};
            int[] test_copies = new int[]{2, 2, 1, 3, 1};
            for (int i = 0; i < 5; i++)
            {
                fw.write(test_title[i] + "\t\t\t" + test_copies[i] + "\t\t\t" + test_price[i] + "\n\n");
            }
        }

        fw.write("————————————————————————————————————————————\n\n");
        fw.write("优惠后总价：\t\t\t\t\t\t");

        if(str.isEmpty())
        {
            Sale sale = new Sale(items);
            fw.write(String.valueOf(sale.getTotal()));
        }
        else
        {
            fw.write(String.valueOf(Test.Test()));
        }

        fw.write("\n\n**************************************欢迎再次光临电子书城！**************************************\n");

        fw.write("                                                  生成时间：");
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fw.write(dateFormat.format(date));

        fw.close();
        JOptionPane.showMessageDialog(null, "成功生成购物清单！", "Success", JOptionPane.OK_OPTION);
    }

}

class MyJDialog_add extends JDialog{
    private static MyJDialog_add instance_jDialog = new MyJDialog_add();
    private MyJDialog_add()
    {

    }
    public static synchronized MyJDialog_add getInstance_jDialog(){return instance_jDialog;}
}

class MyJDialog_test extends JDialog{
    private static MyJDialog_test instance_jDialog = new MyJDialog_test();
    private MyJDialog_test()
    {

    }
    public static synchronized MyJDialog_test getInstance_jDialog(){return instance_jDialog;}
}

class MyJDialog_result extends JDialog{
    private static MyJDialog_result instance_jDialog = new MyJDialog_result();
    private MyJDialog_result()
    {

    }
    public static synchronized MyJDialog_result getInstance_jDialog(){return instance_jDialog;}
}

class MyJDialog_modify extends JDialog{
    private static MyJDialog_modify instance_jDialog = new MyJDialog_modify();
    private MyJDialog_modify()
    {

    }
    public static synchronized MyJDialog_modify getInstance_jDialog(){return instance_jDialog;}
}

class PriceException extends Exception{

}

class PercentException extends Exception{

}
