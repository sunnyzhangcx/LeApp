package data4mooc;

import android.content.Intent;

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

    static Layout createLayout(Basic basic, int homeMode, int homeIndex, Font homeFont, int exampleMode, Font exampleFont, int testMode, Font testFont, int qandaMode,
                               Font qandaFont, TopicLayout layoutTopic, ExampleLayout layoutExample, TestLayout layoutTest, QALayout layoutQA, List<Integer> topicLayout ,List<Integer> exampleLayout){
        Layout.Builder LayoutBuilder = Layout.newBuilder();
        LayoutBuilder.setBasic(basic);
        LayoutBuilder.setHomeMode(homeMode);
        LayoutBuilder.setHomeIndex(homeIndex);
        LayoutBuilder.setHomeFont(homeFont);
        LayoutBuilder.setExampleMode(exampleMode);
        LayoutBuilder.setExampleFont(exampleFont);
        LayoutBuilder.setTestMode(testMode);
        LayoutBuilder.setTestFont(testFont);
        LayoutBuilder.setQandaMode(qandaMode);
        LayoutBuilder.setQandaFont(qandaFont);
        LayoutBuilder.setLayoutTopic(layoutTopic);
        LayoutBuilder.setLayoutExample(layoutExample);
        LayoutBuilder.setLayoutTest(layoutTest);
        LayoutBuilder.setLayoutQA(layoutQA);
        for(Integer topicLayouts : topicLayout){
            LayoutBuilder.addTopicLayout(topicLayouts);
        }
        for(Integer exampleLayouts : exampleLayout){
            LayoutBuilder.addTopicLayout(exampleLayouts);
        }

        return LayoutBuilder.build();
    }

    static Basic createBasic(String title, String version, String intro, String date, String author, String address){
        Basic.Builder BasicBuilder = Basic.newBuilder();
        BasicBuilder.setTitle(title);
        BasicBuilder.setVersion(version);
        BasicBuilder.setIntro(intro);
        BasicBuilder.setDate(date);
        BasicBuilder.setAuthor(author);
        BasicBuilder.setAddress(address);

        return BasicBuilder.build();
    }

    static TopicLayout createTopicLayout(int indexMode, Font name, Font section, Font text, Font resource){
        TopicLayout.Builder builder = TopicLayout.newBuilder();
        builder.setIndexMode(indexMode);
        builder.setName(name);
        builder.setSection(section);
        builder.setText(text);
        builder.setResource(resource);

        return builder.build();
    }

    static TopicLayout createTopicLayout(Font name, Font section, Font text, Font resource){
        TopicLayout.Builder builder = TopicLayout.newBuilder();
        builder.setName(name);
        builder.setSection(section);
        builder.setText(text);
        builder.setResource(resource);

        return builder.build();
    }

    static ExampleLayout createExampleLayout(int indexMode, Font name, Font section, Font text, Font resource){
        ExampleLayout.Builder builder = ExampleLayout.newBuilder();
        builder.setIndexMode(indexMode);
        builder.setName(name);
        builder.setSection(section);
        builder.setText(text);
        builder.setResource(resource);

        return builder.build();
    }

    static ExampleLayout createExampleLayout(Font name, Font section, Font text, Font resource){
        ExampleLayout.Builder builder = ExampleLayout.newBuilder();
        builder.setName(name);
        builder.setSection(section);
        builder.setText(text);
        builder.setResource(resource);

        return builder.build();
    }

    static TestLayout createTestLayout(Font name, Font question, Font answer, Font comment){
        TestLayout.Builder builder = TestLayout.newBuilder();
        builder.setName(name);
        builder.setQuestion(question);
        builder.setAnswer(answer);
        builder.setComment(comment);

        return builder.build();
    }

    static QALayout createQALayout(Font name, Font question, Font answer){
        QALayout.Builder builder = QALayout.newBuilder();
        builder.setName(name);
        builder.setQuestion(question);
        builder.setAnswer(answer);

        return builder.build();
    }

    static QALayout createQALayout(Font question, Font answer){
        QALayout.Builder builder = QALayout.newBuilder();
        builder.setQuestion(question);
        builder.setAnswer(answer);

        return builder.build();
    }

    static Font createFont(int font, int size, int color){
        Font.Builder builder = Font.newBuilder();
        builder.setFont(font);
        builder.setSize(size);
        builder.setColor(color);

        return builder.build();
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
