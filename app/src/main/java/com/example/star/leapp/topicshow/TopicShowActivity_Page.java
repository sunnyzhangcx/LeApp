package com.example.star.leapp.topicshow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bifan.txtreaderlib.bean.TxtMsg;
import com.bifan.txtreaderlib.interfaces.ILoadListener;
import com.bifan.txtreaderlib.main.TxtConfig;
import com.bifan.txtreaderlib.main.TxtReaderView;
import com.example.star.leapp.R;

//我还没有读我那个文档里的内容 就随便找了一些字试的，我写的进这个界面暂时是从知识点目录的三级知识点列表进去的，有三级知识点的只有topic2.2 下面有
//你点最底下的知识目录-》topic2-》topic2.2-》-》然后随便点哪个三级知识点都可以进来了

public class TopicShowActivity_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_show_page);

        TxtReaderView mTxtReaderView = findViewById(R.id.topic_content_page);

        TxtConfig.saveIsOnVerticalPageMode(this,false);
        mTxtReaderView.loadText("package api;" +
                "\n" +
                "import data4mooc.Data4Mooc.*;\n" +
                "\n" +
                "import java.io.*;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "public class DataApi {\n" +
                "    public static List<TNode> setTnode;            //\t知识点节点数组\n" +
                "    public static List<GNode> setGnode;        //\t案例节点数组\n" +
                "    public static List<Test> setTest;            //\t测试题数组\n" +
                "    public static List<QandA> setQA;            //\t常见问题数组\n" +
                "\n" +
                "\n" +
                "    public void load(String filename) {\n" +
                "        //\t若指定文件存在，则文件装入所有内容数据，保存到上述四个数组\n" +
                "        File file = new File(filename);\n" +
                "        if (file.exists()) {\n" +
                "            try {\n" +
                "                MoocData moocData = MoocData.parseFrom(new FileInputStream(filename));\n" +
                "                setTnode = moocData.getSetTNodeList();\n" +
                "                setGnode = moocData.getSetGNodeList();\n" +
                "                setTest = moocData.getSetTestList();\n" +
                "                setQA = moocData.getSetQAList();\n" +
                "            } catch (Exception e) {\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        } else {\n" +
                "            MoocData.Builder moocData = MoocData.newBuilder();\n" +
                "            setTnode=new ArrayList<>();\n" +
                "            setGnode=new ArrayList<>();\n" +
                "            setTest=new ArrayList<>();\n" +
                "            setQA=new ArrayList<>();\n" +
                "            moocData.build();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public boolean save(String filename) {\n" +
                "        //\t将编辑好的数据保存到指定文件；\n" +
                "        MoocData.Builder moocData = MoocData.newBuilder();\n" +
                "        File file = new File(filename);\n" +
                "        if (file.exists()) {\n" +
                "            try {\n" +
                "                System.out.println(\"exit\");\n" +
                "                moocData.mergeFrom(new FileInputStream(file));\n" +
                "            } catch (IOException e) {\n" +
                "                e.printStackTrace();\n" +
                "                return false;\n" +
                "            }\n" +
                "        }\n" +
                "        for (QandA qandA : setQA) {\n" +
                "            moocData.addSetQA(qandA);\n" +
                "        }\n" +
                "        try {\n" +
                "            FileOutputStream outputStream = new FileOutputStream(file);\n" +
                "            moocData.build().writeTo(outputStream);\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }\n" +
                "        return true;\n" +
                "    }\n" +
                "\n" +
                "    /*\t编辑常见问题时用到的数据API\t\t*/\n" +
                "\n" +
                "    public List<String> getAllQuestion() {\n" +
                "        //\t从setQA获取所有常见问题的问题项\n" +
                "        ArrayList<String> questions = new ArrayList<>();\n" +
                "        for (QandA qandA : setQA) {\n" +
                "            String question = qandA.getQuestion();\n" +
                "            questions.add(question);\n" +
                "        }\n" +
                "        return questions;\n" +
                "    }\n" +
                "\n" +
                "    //\t取出第pos个常见问题\n" +
                "    public QandA getQA(int pos) {\n" +
                "        return setQA.get(pos);\n" +
                "    }\n" +
                "\n" +
                "    //\t新建QandA对象，存入setQA；若问题同名的对象已存在，则更新答案\n" +
                "    public void addQA(String question, String answer) {\n" +
                "        boolean exit = false;\n" +
                "        for (QandA qa : setQA) {\n" +
                "            if (qa.getQuestion().equals(question)) {\n" +
                "                qa.toBuilder().setQuestion(question).build();\n" +
                "                exit = true;\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        if (!exit) {\n" +
                "            QandA.Builder qandA = QandA.newBuilder();\n" +
                "            qandA.setQuestion(question);\n" +
                "            qandA.setAnswer(answer);\n" +
                "            if (setQA.size()>1){\n" +
                "                qandA.setNo(setQA.get(setQA.size() - 1).getNo() + 1);\n" +
                "            }else {\n" +
                "                qandA.setNo(1);\n" +
                "            }\n" +
                "//            qandA.addTopicList(1);\n" +
                "            setQA.add(qandA.build());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //\t根据指定问题，删除setQA中的QandA对象；\n" +
                "    public void removeQA(String question) {\n" +
                "        for (QandA qandA : setQA) {\n" +
                "            if (qandA.getQuestion().equals(question)) {\n" +
                "                setQA.remove(qandA);\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //\t将第pos1个常见问题移到pos2位置\n" +
                "    public void moveQA(int pos1, int pos2) {\n" +
                "        QandA qandA = setQA.get(pos1);\n" +
                "        setQA.remove(pos1);\n" +
                "        setQA.add(pos1, setQA.get(pos2));\n" +
                "        setQA.remove(pos2);\n" +
                "        setQA.add(pos2, qandA);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    /*\t编辑常见问题相关知识点时用到的数据API\t*/\n" +
                "    public List<String> getAllTopic() {\n" +
                "        //\t从setTnode获取所有知识点的名字；\n" +
                "        ArrayList<String> topics = new ArrayList<>();\n" +
                "        for (TNode tNode : setTnode) {\n" +
                "            topics.add(tNode.getTopic().getTitle());\n" +
                "        }\n" +
                "        return topics;\n" +
                "    }\n" +
                "\n" +
                "    public void addTopic(QandA qa, String topic) {\n" +
                "        //\t根据指定知识点topic查找TNode节点，将其下标添加到QandA对象qa中\n" +
                "        for (TNode tNode : setTnode) {\n" +
                "            if (tNode.getTopic().getTitle().equals(topic)) {\n" +
                "                qa.getTopicListList().add(setTnode.indexOf(tNode));\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public List<String> getTopicList(QandA qa) {\n" +
                "        //\t从指定对象qa中的知识点节点下标，获得所有知识点的名字；\n" +
                "        ArrayList<String> topicNames = new ArrayList<>();\n" +
                "        List<Integer> topicList = qa.getTopicListList();\n" +
                "        for (Integer i : topicList) {\n" +
                "            topicNames.add(setTnode.get(i).getTopic().getTitle());\n" +
                "        }\n" +
                "        return topicNames;\n" +
                "    }\n" +
                "\n" +
                "    public void remove(QandA qa, String topic) {\n" +
                "        //\t根据指定知识点topic查找TNode节点，将其下标从QandA对象qa中删除；\n" +
                "        for (TNode tNode : setTnode) {\n" +
                "            if (tNode.getTopic().getTitle().equals(topic)) {\n" +
                "                qa.getTopicListList().remove(setTnode.indexOf(tNode));\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    //\t测试题编辑用的数据API\n" +
                "    public List<String> getAllTest() {\n" +
                "        //\t从setTest获取所有测试题的名字；\n" +
                "        ArrayList<String> testNames = new ArrayList();\n" +
                "        for (Test test : setTest) {\n" +
                "            testNames.add(test.getTitle());\n" +
                "        }\n" +
                "        return testNames;\n" +
                "    }\n" +
                "\n" +
                "    public Test getTest(int pos) {\n" +
                "        //\t取出第pos个测试题\n" +
                "        return setTest.get(pos);\n" +
                "    }\n" +
                "\n" +
                "    public void addTest(String name) {\n" +
                "        //\t新建Test对象，存入setTest；不允许同名的对象存在\n" +
                "        boolean exit = false;\n" +
                "        for (Test test : setTest) {\n" +
                "            if ((test.getTitle().equals(name))) {\n" +
                "                exit = true;\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        if (!exit) {\n" +
                "            Test.Builder test = Test.newBuilder();\n" +
                "            test.setTitle(name);\n" +
                "            setTest.add(test.build());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void removeTest(String name) {\n" +
                "        //\t根据指定名字，删除setTest中的Test对象；\n" +
                "        for (Test test : setTest) {\n" +
                "            if (test.getTitle().equals(name)) {\n" +
                "                setTest.remove(test);\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public void moveTest(int pos1, int pos2) {\n" +
                "        //\t将第pos1个测试题移到第pos2个位置\n" +
                "        Test test = setTest.get(pos1);\n" +
                "        setTest.remove(pos1);\n" +
                "        setTest.add(pos1, setTest.get(pos2));\n" +
                "        setTest.remove(pos2);\n" +
                "        setTest.add(pos2, test);\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    public void addText(Test test, String text) {\n" +
                "        //\t在test对象中添加文本text\n" +
                "    }\n" +
                "\n" +
                "    public void addPicture(Test test, String picfile) {\n" +
                "        //\t在test对象中添加图片（文件名）\n" +
                "    }\n" +
                "\n" +
                "    public Item getItem(Test test, int pos) {\n" +
                "        //\t获得test对象中的第pos个内容项（文本或图片）\n" +
                "        return test.getItems(pos);\n" +
                "    }\n" +
                "\n" +
                "    public void addResult(Test test, String altertive, String result, String comment, String topicname) {\n" +
                "        //\t创建result对象，添加到test对象；（知识点名字需要转换为下标）\n" +
                "        Result.Builder resultBuilder=Result.newBuilder();\n" +
                "        resultBuilder.setAltertive(altertive);\n" +
                "        resultBuilder.setResult(result);\n" +
                "        resultBuilder.setComment(comment);\n" +
                "        for (TNode tNode:setTnode){\n" +
                "            if (tNode.getTopic().equals(topicname)){\n" +
                "                resultBuilder.setTopic(setTnode.indexOf(tNode));\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        test.getResultsList().add(resultBuilder.build());\n" +
                "    }\n" +
                "\n" +
                "    public Result getResult(Test test, int pos) {\n" +
                "        //\t从test对象获得第pos个Result对象\n" +
                "        return test.getResults(pos);\n" +
                "    }\n" +
                "\n" +
                "    public void remove(Test test, int pos) {\n" +
                "        //\t从test对象删除第pos个Result对象\n" +
                "        test.getResultsList().remove(pos);\n" +
                "    }\n" +
                "\n" +
                "    public void setTestAttr(Test test, int diff, int kind, int type) {\n" +
                "        //\t设置test对象的难度、种类和类型\n" +
                "        Test.Builder tb=test.toBuilder();\n" +
                "        tb.setDifficulty(diff);\n" +
                "        tb.setKind(kind);\n" +
                "        tb.setType(type);\n" +
                "        tb.build();\n" +
                "    }\n" +
                "\n" +
                "    //\t案例编译用的数据API\n" +
                "    public List<String> getAllExample() {\n" +
                "        //\t从setExample获取所有案例的名字；\n" +
                "        ArrayList<String> exampleNames=new ArrayList<>();\n" +
                "        for (GNode gNode:setGnode){\n" +
                "            exampleNames.add(gNode.getExample().getTitle());\n" +
                "        }\n" +
                "        return exampleNames;\n" +
                "    }\n" +
                "\n" +
                "    public Example getExample(int pos) {\n" +
                "        //\t取出第pos个案例\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void addExample(String name) {\n" +
                "        //\t新建Example对象，存入setExample；不允许同名的对象存在\n" +
                "    }\n" +
                "\n" +
                "    public void removeExample(String name) {\n" +
                "        //\t根据指定名字，删除setExample中的Example对象；\n" +
                "    }\n" +
                "\n" +
                "    public void moveExample(int pos1, int pos2) {\n" +
                "        //将第pos1个案例对象移到第pos2位置\n" +
                "    }\n" +
                "\n" +
                "    public Section addSection(Example ex, String sect) {\n" +
                "        //\t创建小节对象，添加到Example对象ex后，返回该对象\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public Section getSection(Example ex, int pos) {\n" +
                "        //\t获得案例对象ex中的第pos小节\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void removeSection(Example ex, int pos) {\n" +
                "        //\t删除案例对象ex中的第pos小节\n" +
                "    }\n" +
                "\n" +
                "    public void addText(Section sec, String text) {\n" +
                "        //\t在指定小节中添加文本\n" +
                "    }\n" +
                "\n" +
                "    public void addPicture(Section sec, String file) {\n" +
                "        //\t在sec对象中添加本地资源文件名（图片、音频、视频）\n" +
                "    }\n" +
                "\n" +
                "    public void addResouce(Section sec, String resource) {\n" +
                "        //\t在sec对象中添加外部文件（URI）\n" +
                "    }\n" +
                "\n" +
                "    public void addTopic(Section sec, String topic) {\n" +
                "        //\t在sec对象中添加知识点引用下标\n" +
                "    }\n" +
                "\n" +
                "    public Item getItem(Section sec, int pos) {\n" +
                "        //\t获得sec对象中的第pos个内容项（文本或图片）\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void remove(Section sec, int pos) {\n" +
                "        //\t删除sec对象中的第pos个内容项\n" +
                "    }\n" +
                "\n" +
                "    public void addFrom(Example ex, String name) {\n" +
                "        //\t根据案例名name查找GNode节点，将其下标作为先修案例添加到对象ex；\n" +
                "    }\n" +
                "\n" +
                "    public List<String> getFrom(Example ex) {\n" +
                "        //\t从指定对象ex中的先修案例节点下标，获得所有案例的名字；\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void remove(Example ex, String name) {\n" +
                "        //\t根据指定案例名name查找GNode节点，将其下标从对象ex中删除先修案例；\n" +
                "    }\n" +
                "\n" +
                "    public void setExampleAttr(Example ex, int intro, int type, int model) {\n" +
                "        //\t设置案例的简介、类型和页面样式\n" +
                "    }\n" +
                "\n" +
                "    //\t知识点编辑用的数据API\n" +
                "    public String getRoot() {\n" +
                "        //\t获得知识点树的根节点中知识点的名称\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public List<String> getChild(String name) {\n" +
                "        //\t找到名为name的知识点的树节点，返回它的所有子节点的知识点的名称\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public TNode getTopic(String name) {\n" +
                "        //\t按照给定name，找到对应Topic对象的TNode对象\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void addTopic(TNode node, String name) {\n" +
                "        //\t创建名为name的知识点，作为知识点节点的子节点添加。\n" +
                "    }\n" +
                "\n" +
                "    public TNode removeTopic(TNode node, String name) {\n" +
                "        //\t从TNode的子树中删除并返回名为name的知识点节点。\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void insertNode(TNode node, int pos, TNode child) {\n" +
                "        //\t将知识树节点child，插入到node的子节点中第pos个节点的前面\n" +
                "    }\n" +
                "\n" +
                "    public Section addSection(Topic tpc, String sect) {\n" +
                "        //\t创建小节对象，添加到Topic对象tpc后，返回该对象\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public Section getSection(Topic tpc, int pos) {\n" +
                "        //\t获得案例对象ex中的第pos小节\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void removeSection(Topic tpc, int pos) {\n" +
                "        //\t删除案例对象ex中的第pos小节\n" +
                "    }\n" +
                "\n" +
                "    public List<String> getExampleList(Topic tpc) {\n" +
                "        //\t获得知识点对象tpc中的所有相关案例的名称\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public List<Integer> getWeightList(Topic tpc) {\n" +
                "        //\t获得知识点对象tpc中所有相关案例的权重\n" +
                "        return null;\n" +
                "    }\n" +
                "\n" +
                "    public void addExample(Topic tpc, String name, int weight) {\n" +
                "        // \t按照名字name找到该案例下标，连同权重保存到tpc对象的相关属性\n" +
                "    }\n" +
                "\n" +
                "    public void removeExample(Topic tpc, String name) {\n" +
                "        //\t从知识点对象tpc中，删除name指定的相关案例下标和权重\n" +
                "    }\n" +
                "\n" +
                "    public void setExampleAttr(Topic tpc, int intro, int type, int level, int model) {\n" +
                "        //\t设置知识点tpc的简介、类型、学习要求和页面样式\n" +
                "    }\n" +
                "\n" +
                "}\n", new ILoadListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail(TxtMsg txtMsg) {

            }

            @Override
            public void onMessage(String message) {

            }
        });

    }
}
