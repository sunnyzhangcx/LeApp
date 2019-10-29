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

    public static String FilePath = "/data/data/com.example.star.leapp/files/data.txt";


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

        int[] child1 = {1,7,12};
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
        ArrayList<Data4Mooc.Result> resultList1 = new ArrayList<>();
        ArrayList<Data4Mooc.Result> resultList2 = new ArrayList<>();
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
        resultList1.add(MoocCreator.createResult("E.候选项/填空序号-result5.1","false","解释-comment5.3.....................................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.............",1));

        resultList2.add(MoocCreator.createResult("A.候选项/填空序号-result1.1","false","解释-comment1.3.................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000................................0000000000000000000000000000000000000000000000000000000000..........",1));
        resultList2.add(MoocCreator.createResult("B.候选项/填空序号-result2.1","true","解释-comment2.3................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000..................",1));
        resultList2.add(MoocCreator.createResult("C.候选项/填空序号-result3.1","false","解释-comment3.3...............................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000.......00000000000000000000000000....0000000000000000000000000000000000000000000000.............",1));
        resultList2.add(MoocCreator.createResult("D.候选项/填空序号-result4.1","true","解释-comment4.3.......................................................................................................................................................................................................................................................................................................0000000000000000000000000000000000000000000000000000000000000000000000000000000..............00000000000000000000000000000000000000000000000000............",1));
        resultList2.add(MoocCreator.createResult("E.候选项/填空序号-result5.1","false","解释-comment5.3.....................................................................................................................................................................................................................................................................................................000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.............",1));


        testList.add(MoocCreator.createTest("1.测验标题-test1",0,1,0,itemList,resultList1));
        testList.add(MoocCreator.createTest("2.测验标题-test2",1,2,1,itemList,resultList2));
        testList.add(MoocCreator.createTest("3.测验标题-test3",2,3,2,itemList,resultList2));
        testList.add(MoocCreator.createTest("4.测验标题-test4",3,4,3,itemList,resultList2));

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
