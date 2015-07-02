package org.eastbar.web.report.dao;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.eastbar.web.report.entity.Test;

import java.util.ArrayList;
import java.util.List;

public class MockDataFactory {
	
	public MockDataFactory()
	{
		System.out.println("create mock up data");
	}

    public JRDataSource getTestData(){
        List<Test> listData = new ArrayList<Test>();
        Test test1=new Test();
        test1.setId(1);
        test1.setName("张三");
        test1.setTime("1990-02-14");

        Test test2=new Test();
        test2.setId(2);
        test2.setName("李四");
        test2.setTime("1992-12-01");
        Test test3=new Test();
        test3.setId(3);
        test3.setName("王五");
        test3.setTime("1992-11-18");

        Test test4=new Test();
        test4.setId(4);
        test4.setName("马六");
        test4.setTime("1992-11-18");
        Test test5=new Test();
        test5.setId(1);
        test5.setName("张5");
        test5.setTime("1990-02-14");
        Test test6=new Test();
        test6.setId(1);
        test6.setName("张6");
        test6.setTime("1990-02-14");

        Test test7=new Test();
        test7.setId(1);
        test7.setName("张7");
        test7.setTime("1990-02-14");
        Test test8=new Test();
        test8.setId(1);
        test8.setName("张8");
        test8.setTime("1990-02-14");
        Test test9=new Test();
        test9.setId(9);
        test9.setName("张三9");
        test9.setTime("1990-02-14");
        Test test10=new Test();
        test10.setId(10);
        test10.setName("张三10");
        test10.setTime("1990-02-14");
        Test test11=new Test();
        test11.setId(11);
        test11.setName("张三11");
        test11.setTime("1990-02-14");
        Test test12=new Test();
        test12.setId(12);
        test12.setName("张三12");
        test12.setTime("1990-02-14");
        Test test13=new Test();
        test13.setId(13);
        test13.setName("张三13");
        test13.setTime("1990-02-14");
        Test test14=new Test();
        test14.setId(14);
        test14.setName("张三14");
        test14.setTime("1990-02-14");
        Test test15=new Test();
        test15.setId(15);
        test15.setName("张三15");
        test15.setTime("1990-02-14");
        test14.setId(16);
        Test test16=new Test();
        test16.setName("张三16");
        test16.setTime("1990-02-14");
        Test test17=new Test();
        test17.setId(17);
        test17.setName("张三17");
        test17.setTime("1990-02-14");
        Test test18=new Test();
        test18.setId(18);
        test18.setName("张三18");
        test18.setTime("1990-02-14");
        Test test19=new Test();
        test19.setId(19);
        test19.setName("张三14");
        test19.setTime("1990-02-14");
        Test test20=new Test();
        test20.setId(20);
        test20.setName("张三20");
        test20.setTime("1990-02-14");
        Test test21=new Test();
        test21.setId(21);
        test21.setName("张三21");
        test21.setTime("1990-02-14");
        Test test22=new Test();
        test22.setId(22);
        test22.setName("张三22");
        test22.setTime("1990-02-14");
        Test test23=new Test();
        test23.setId(23);
        test23.setName("张三23");
        test23.setTime("1990-02-14");
        test23.setId(14);
        test14.setName("张三14");
        test14.setTime("1990-02-14");
        Test test24=new Test();
        test24.setId(14);
        test24.setName("张三24");
        test24.setTime("1990-02-14");
        Test test25=new Test();
        test25.setId(25);
        test25.setName("张三25");
        test14.setTime("1990-02-14");
        Test test26=new Test();
        test26.setId(26);
        test26.setName("张三26");
        test26.setTime("1990-02-14");
        Test test27=new Test();
        test27.setId(27);
        test27.setName("张三27");
        test27.setTime("1990-02-14");
        Test test28=new Test();
        test28.setId(28);
        test28.setName("张三28");
        test28.setTime("1990-02-14");
        Test test29=new Test();
        test29.setId(29);
        test29.setName("张三29");
        test29.setTime("1990-02-14");
        Test test30=new Test();
        test30.setId(30);
        test30.setName("张三30");
        test30.setTime("1990-02-14");


        listData.add(test1);
        listData.add(test2);
        listData.add(test3);
        listData.add(test4);
        listData.add(test5);
        listData.add(test6);
        listData.add(test7);
        listData.add(test8);
        listData.add(test9);
        listData.add(test10);
        listData.add(test11);
        listData.add(test12);
        listData.add(test13);
        listData.add(test14);
        listData.add(test15);
        listData.add(test16);
        listData.add(test17);
        listData.add(test18);
        listData.add(test19);
        listData.add(test20);
        listData.add(test21);
        listData.add(test22);
        listData.add(test23);
        listData.add(test24);
        listData.add(test25);
        listData.add(test26);
        listData.add(test27);
        listData.add(test28);
        listData.add(test29);
        listData.add(test30);
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(listData);
        return data;
    }

}

