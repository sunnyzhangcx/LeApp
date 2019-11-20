package data4mooc;

import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MoocWriter {

    public static String FilePath = "/data/data/com.example.star.leapp/files/Readme.zxz";

    //Q&A常见问题写入
    public static boolean writeqaList(ArrayList<Data4Mooc.QandA> qaList, String filepath, boolean append){
        File qaFile = new File(filepath);
        if(!qaFile.exists()){
            if(!qaFile.getParentFile().exists()){
                qaFile.getParentFile().mkdirs();
            }
            try {
                qaFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data4Mooc.MoocData.Builder moocDataBuilder = Data4Mooc.MoocData.newBuilder();
        for(Data4Mooc.QandA qa:qaList){
            moocDataBuilder.addSetQA(qa);
        }
        Data4Mooc.MoocData moocData = moocDataBuilder.build();
        try  {
            OutputStream os=new FileOutputStream(filepath,append);
            moocData.writeTo(os);
            os.close();
        }catch  (FileNotFoundException e){
            Log.e("error","file not exist!");
            return  false;
        } catch (IOException e) {
            Log.e("error","write failed!");
            return false;
        }
        return true;
    }

    //Test测试题写入
    public static boolean writeTestList(ArrayList<Data4Mooc.Test> testList, String filepath, boolean append){
        File file = new File(filepath);
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data4Mooc.MoocData.Builder moocDataBuilder = Data4Mooc.MoocData.newBuilder();
        for(Data4Mooc.Test test:testList){
            moocDataBuilder.addSetTest(test);
        }
        Data4Mooc.MoocData moocData = moocDataBuilder.build();
        try  {
            OutputStream os=new FileOutputStream(filepath,append);
            moocData.writeTo(os);
            os.close();
        }catch  (FileNotFoundException e){
            Log.e("error","file not exist!");
            return  false;
        } catch (IOException e) {
            Log.e("error","write failed!");
            return false;
        }
        return true;
    }

    //Topic知识点写入（按照树节点集合TNode写入）
    public static boolean writeTNodeList(ArrayList<Data4Mooc.TNode> TNodeList, String filepath, boolean append){
        File file = new File(filepath);
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data4Mooc.MoocData.Builder moocDataBuilder = Data4Mooc.MoocData.newBuilder();
        for(Data4Mooc.TNode tNode:TNodeList){
            moocDataBuilder.addSetTNode(tNode);
        }
        Data4Mooc.MoocData moocData = moocDataBuilder.build();
        try  {
            OutputStream os=new FileOutputStream(filepath,append);
            moocData.writeTo(os);
            os.close();
        }catch  (FileNotFoundException e){
            Log.e("error","file not exist!");
            return  false;
        } catch (IOException e) {
            Log.e("error","write failed!");
            return false;
        }
        return true;
    }

/*    //页面布局参数写入
    public static boolean writeLayout(ArrayList<Data4Mooc.Layout> layouts, String filepath, boolean append){
        File file = new File(filepath);
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data4Mooc.MoocData.Builder moocDataBuilder = Data4Mooc.MoocData.newBuilder();
        moocDataBuilder.
        Data4Mooc.MoocData moocData = moocDataBuilder.build();
        try  {
            OutputStream os=new FileOutputStream(filepath,append);
            moocData.writeTo(os);
            os.close();
        }catch  (FileNotFoundException e){
            Log.e("error","file not exist!");
            return  false;
        } catch (IOException e) {
            Log.e("error","write failed!");
            return false;
        }
        return true;
    }*/

    public static boolean writeGNodeList(ArrayList<Data4Mooc.GNode> GNodeList, String filepath, boolean append){
        File file = new File(filepath);
        if(!file.exists()){
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data4Mooc.MoocData.Builder moocDataBuilder = Data4Mooc.MoocData.newBuilder();
        for(Data4Mooc.GNode gNode:GNodeList){
            moocDataBuilder.addSetGNode(gNode);
        }
        Data4Mooc.MoocData moocData = moocDataBuilder.build();
        try  {
            OutputStream os=new FileOutputStream(filepath,append);
            moocData.writeTo(os);
            os.close();
        }catch  (FileNotFoundException e){
            Log.e("error","file not exist!");
            return  false;
        } catch (IOException e) {
            Log.e("error","write failed!");
            return false;
        }
        return true;
    }

    public static void writeMoocData(boolean append){

        int[] child1 = {0,1,7,12};
        int[] child2 = {2,3,4};
        int[] child3 = {8,9};
        int[] child4 = {13};
        int[] child5 = {5,6};
        int[] child6 = {10,11};
        int[] child0 = {};

        String fileName = FilePath;
        //添加数据
        ArrayList<Data4Mooc.QandA> qaList = new ArrayList<>();
        ArrayList<Data4Mooc.Test> testList = new ArrayList<>();
        ArrayList<Data4Mooc.Item> itemList = new ArrayList<>();
        ArrayList<Data4Mooc.Item> result3ItemList = new ArrayList<>();
        ArrayList<Data4Mooc.Item> result4ItemList = new ArrayList<>();
        ArrayList<Data4Mooc.Result> resultList1 = new ArrayList<>();
        ArrayList<Data4Mooc.Result> resultList2 = new ArrayList<>();
        ArrayList<Data4Mooc.Result> resultList3 = new ArrayList<>();
        ArrayList<Data4Mooc.Result> resultList4 = new ArrayList<>();
        ArrayList<Data4Mooc.TNode> tNodeArrayList = new ArrayList<>();
        ArrayList<Data4Mooc.Section> sectionArrayList = new ArrayList<>();
        ArrayList<Integer> examples = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        ArrayList<Data4Mooc.GNode> gNodeArrayList = new ArrayList<>();
        List<Integer> topicList = new ArrayList<>();
        topicList.add(2);topicList.add(4);topicList.add(5);topicList.add(7);
        qaList.add(MoocCreator.createQA(1,"常见问题-test1","常见问题-answer1",topicList));
        qaList.add(MoocCreator.createQA(2,"常见问题-test2","常见问题-answer2",topicList));
        qaList.add(MoocCreator.createQA(3,"常见问题-test3","常见问题-answer3",topicList));
        qaList.add(MoocCreator.createQA(4,"常见问题-test4","常见问题-answer4.....",topicList));

        itemList.add(MoocCreator.createItem(1,"内容二元组-item1..............................................................................1111111111111111111111111111111111111111111111111111111111111122222222222222222222222222222222222222222222222222222222233333333333333333333333333"));
        itemList.add(MoocCreator.createItem(2,"内容二元组-item2hskdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakjdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgrkdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakjdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgrdf"));
        itemList.add(MoocCreator.createItem(3,"内容二元组-item3kdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakjdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgrkjdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgr"));
        itemList.add(MoocCreator.createItem(4,"内容二元组-item4kdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakjkdfhskaldhfalksjdhfsdkflsahdkflsjahklhdfklsahdfjasdfhlksadfhsakljdfhskjdfhskjdfhskldfhsakjdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgrdfhsakljdfhsadkjlfhsakjdlfhsadjklfhsalkdfhskdjfhslakdfjhslakdfhkjsdsdfsadfasdgdfhgfrtjtrhgr"));

        resultList1.add(MoocCreator.createResult("A.候选项/填空序号-result1.1","false","解释-comment1.3.................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000................................0000000000000000000000000000000000000000000000000000000000..........",1));
        resultList1.add(MoocCreator.createResult("B.候选项/填空序号-result2.1","true","解释-comment2.3................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000..................",1));
        resultList1.add(MoocCreator.createResult("C.候选项/填空序号-result3.1","false","解释-comment3.3...............................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000.......00000000000000000000000000....0000000000000000000000000000000000000000000000.............",1));
        resultList1.add(MoocCreator.createResult("D.候选项/填空序号-result4.1","false","解释-comment4.3.......................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000..............00000000000000000000000000000000000000000000000000............",1));

        resultList2.add(MoocCreator.createResult("A.候选项/填空序号-result1.1","false","解释-comment1.3.................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000................................0000000000000000000000000000000000000000000000000000000000.........."));
        resultList2.add(MoocCreator.createResult("B.候选项/填空序号-result2.1","true","解释-comment2.3................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000..................",1));
        resultList2.add(MoocCreator.createResult("C.候选项/填空序号-result3.1","false","解释-comment3.3...............................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000.......00000000000000000000000000....0000000000000000000000000000000000000000000000............."));
        resultList2.add(MoocCreator.createResult("D.候选项/填空序号-result4.1","true","解释-comment4.3.......................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000..............00000000000000000000000000000000000000000000000000............",1));
        resultList2.add(MoocCreator.createResult("E.候选项/填空序号-result5.1","false","解释-comment5.3.....................................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.............",1));

        resultList3.add(MoocCreator.createResult("1.","a","大雪：纷纷扬扬的大雪",1));
        resultList3.add(MoocCreator.createResult("2.","b","白茫茫：天地间白茫茫的一片"));
        resultList3.add(MoocCreator.createResult("3.","c","铁路：不知道是铁路还是公路，瞎选的",1));
        resultList3.add(MoocCreator.createResult("4.","a","大雪：纷纷扬扬的大雪",1));
        resultList3.add(MoocCreator.createResult("5.","b","白茫茫：天地间白茫茫的一片"));
        resultList3.add(MoocCreator.createResult("6.","c","铁路：不知道是铁路还是公路，瞎选的",1));
        resultList3.add(MoocCreator.createResult("7.","a","大雪：纷纷扬扬的大雪",1));
        resultList3.add(MoocCreator.createResult("8.","b","白茫茫：天地间白茫茫的一片"));
        resultList3.add(MoocCreator.createResult("9.","c","铁路：不知道是铁路还是公路，瞎选的",1));
        resultList3.add(MoocCreator.createResult("10.","a","大雪：纷纷扬扬的大雪",1));
        resultList3.add(MoocCreator.createResult("11.","b","白茫茫：天地间白茫茫的一片"));
        resultList3.add(MoocCreator.createResult("12.","c","铁路：不知道是铁路还是公路，瞎选的",1));




        resultList4.add(MoocCreator.createResult("<1> 材料一、二分别体现了我国政府实施什么战略？两者的关系怎么样？","a","什么战略（两个）"));
        resultList4.add(MoocCreator.createResult("<2> 结合<1>的结论，请你谈谈在社会主义现代化建设中我们为什么要高度重视提高人的素质？","b","两者的关系；两者的作用；" ));
        resultList4.add(MoocCreator.createResult("<3> 结合<2>的结论，请你谈谈在社会主义现代化建设中我们为什么要高度重视提高人的素质？","b","两者的关系；两者的作用；" ));
        resultList4.add(MoocCreator.createResult("<4> 材料一、二分别体现了我国政府实施什么战略？两者的关系怎么样？","a","什么战略（两个）"));
        resultList4.add(MoocCreator.createResult("<5> 结合<1>的结论，请你谈谈在社会主义现代化建设中我们为什么要高度重视提高人的素质？","b","两者的关系；两者的作用；" ));


        result3ItemList.add(MoocCreator.createItem(-1,"纷纷扬扬的____<1>____下了半尺多厚。天地间____<2>____的一片。我顺着____<3>____工地走了四十多公里，只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。"));

        result4ItemList.add(MoocCreator.createItem(-1,"材料一：“十五”期间，国家要推进科技进步与创新，提交可持续发展能力；加快教育发展，提高全民族素质，壮大人才队伍。\n 材料二“十五”期间，国家要控制人口增长，提高出生人口素质：节约、保护资源，实现持续利用；加强生态环境建设，保护和治理环境。"));

        testList.add(MoocCreator.createTest("1.单选测验标题-test1",0,1,0,itemList,resultList1));
        testList.add(MoocCreator.createTest("2.多选测验标题-test2",1,2,1,itemList,resultList2));
        testList.add(MoocCreator.createTest("3.填空测验标题-test3",2,3,2,result3ItemList,resultList3));
        testList.add(MoocCreator.createTest("4.简答测验标题-test4",3,4,3,result4ItemList,resultList4));

        sectionArrayList.add(MoocCreator.createSection("Section1",itemList));
        sectionArrayList.add(MoocCreator.createSection("Section2",itemList));
        sectionArrayList.add(MoocCreator.createSection("Section3",itemList));
        sectionArrayList.add(MoocCreator.createSection("Section4",itemList));
        sectionArrayList.add(MoocCreator.createSection("Section5",itemList));
        sectionArrayList.add(MoocCreator.createSection("Section6",itemList));

        examples.add(1);examples.add(2);examples.add(3);
        weights.add(1);weights.add(2);weights.add(3);

        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("树根root","介绍",0,1,sectionArrayList,examples,weights),child1));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1","介绍",1,1,sectionArrayList,examples,weights),child2));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1.1","介绍",2,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1.2","介绍",3,1,sectionArrayList,examples,weights),child5));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1.3","介绍",4,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1.2.1","介绍",5,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic1.2.2","介绍",6,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic2","介绍",7,1,sectionArrayList,examples,weights),child3));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic2.1","介绍",8,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic2.2","介绍",9,1,sectionArrayList,examples,weights),child6));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic2.2.1","介绍",10,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic2.2.2","介绍",11,1,sectionArrayList,examples,weights),child0));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic3","介绍",12,1,sectionArrayList,examples,weights),child4));
        tNodeArrayList.add(MoocCreator.createTNode(MoocCreator.createTopic("Topic3.1","介绍",13,1,sectionArrayList,examples,weights),child0));

        //先修案例索引
        int[] from0 = {};
        int[] from1 = {0};
        int[] from2 = {0,1,3};
        int[] from3 = {0};
        int[] from4 = {1,2,5};
        int[] from5 = {2,3};
        int[] from6 = {4,5};

        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example0","intro0",0,sectionArrayList),from0));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example1","intro1",1,sectionArrayList),from1));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example2","intro2",2,sectionArrayList),from2));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example3","intro3",3,sectionArrayList),from3));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example4","intro4",4,sectionArrayList),from4));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example5","intro5",5,sectionArrayList),from5));
        gNodeArrayList.add(MoocCreator.createGNode(MoocCreator.createExample("Example6","intro6",6,sectionArrayList),from6));


        MoocWriter.writeqaList(qaList,fileName,append);
        MoocWriter.writeTestList(testList,fileName,append);
        MoocWriter.writeTNodeList(tNodeArrayList,fileName,append);
        MoocWriter.writeGNodeList(gNodeArrayList,fileName,append);

    }
}
