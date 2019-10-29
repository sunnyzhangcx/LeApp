package data4mooc;


import android.os.IBinder;

import java.util.List;

import static data4mooc.Data4Mooc.*;

public class MoocCreator {
    /**
     * all param 有参构造QandA对象
     *
     * @param no
     * @param question
     * @param answer
     * @param topicList
     * @return
     */
    static QandA createQA(int no, String question, String answer, List<Integer> topicList) {
        QandA.Builder QABuilder = QandA.newBuilder();
        QABuilder.setNo(no);
        QABuilder.setQuestion(question);
        QABuilder.setAnswer(answer);
        for (int topic : topicList) {
            QABuilder.addTopicList(topic);
        }
        return QABuilder.build();
    }

    static Item createItem(int type, String content) {
        Item.Builder ItemBuilder = Item.newBuilder();
        ItemBuilder.setType(type);
        ItemBuilder.setContent(content);
        return ItemBuilder.build();
    }

    static Result createResult(String altertive, String result, String comment, int topic) {
        Result.Builder ResultBuilder = Result.newBuilder();
        ResultBuilder.setAltertive(altertive);
        ResultBuilder.setResult(result);
        ResultBuilder.setComment(comment);
        ResultBuilder.setTopic(topic);
        return ResultBuilder.build();

    }

    public static Result createResult(String altertive, String result, String comment) {
        Result.Builder ResultBuilder = Result.newBuilder();
        ResultBuilder.setAltertive(altertive);
        ResultBuilder.setResult(result);
        ResultBuilder.setComment(comment);
        return ResultBuilder.build();

    }

    static Test createTest(String title, int kind, int type, int difficulty, List<Item> items, List<Result> results) {
        Test.Builder TestBuilder = Test.newBuilder();
        TestBuilder.setTitle(title);
        TestBuilder.setKind(kind);
        TestBuilder.setType(type);
        TestBuilder.setDifficulty(difficulty);
        for (Item item : items) {
            TestBuilder.addItems(item);
        }
        for (Result result : results) {
            TestBuilder.addResults(result);
        }

        return TestBuilder.build();
    }

    static TNode createTNode(Topic topic, int[] child) {
        TNode.Builder TNodeBuilder = TNode.newBuilder();
        TNodeBuilder.setTopic(topic);
        for (int child1 : child) {
            TNodeBuilder.addChild(child1);
        }

        return TNodeBuilder.build();
    }

    static Topic createTopic(String title, String intro, int level, int type, List<Section> sections, List<Integer> examples, List<Integer> weights) {
        Topic.Builder TopicBuilder = Topic.newBuilder();
        TopicBuilder.setTitle(title);
        TopicBuilder.setIntro(intro);
        TopicBuilder.setLevel(level);
        TopicBuilder.setType(type);
        for (Section section : sections) {
            TopicBuilder.addSections(section);
        }
        for (int example : examples) {
            TopicBuilder.addExamples(example);
        }
        for (int weight : weights) {
            TopicBuilder.addWeights(weight);
        }

        return TopicBuilder.build();
    }

    static Section createSection(String title, List<Item> items) {
        Section.Builder SectionBuilder = Section.newBuilder();
        SectionBuilder.setTitle(title);
        for (Item item : items) {
            SectionBuilder.addItems(item);
        }
        return SectionBuilder.build();
    }


    static Example createExample(String title, String intro, int type, List<Section> sections) {
        Example.Builder ExampleBuilder = Example.newBuilder();
        ExampleBuilder.setTitle(title);
        ExampleBuilder.setIntro(intro);
        ExampleBuilder.setType(type);
        for (Section section : sections) {
            ExampleBuilder.addSections(section);
        }
        return ExampleBuilder.build();
    }

    static GNode createGNode(Example example, int[] from) {
        GNode.Builder GNodeBuilder = GNode.newBuilder();
        GNodeBuilder.setExample(example);
        for (int forms : from) {
            GNodeBuilder.addFrom(forms);
        }
        return GNodeBuilder.build();
    }
/*    public static QandA creatQA(){
        QandA.Builder QABuilder = QandA.newBuilder();
        QABuilder.setNo(1);
        QABuilder.setQuestion("question-test");
        QABuilder.setAnswer("answer-test");
        QABuilder.addTopicList(8);
        QandA qa = QABuilder.build();
        return qa;
    }*/

}
