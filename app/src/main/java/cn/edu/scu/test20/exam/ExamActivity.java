package cn.edu.scu.test20.exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cn.edu.scu.test20.R;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="ExamActivity";
    private static final String KET_INDEX="index";
    private static final int REQUEST_CODE_RESULT=0;

    private TextView tvExit;  //退出测试
    private TextView tvQue;   //
    private RadioGroup rgOption;
    private RadioButton rbOptionl,rbOption2, rbOption3, rbOption4;
    private TextView tvPre;
    private TextView tvNext;
    private TextView tvAnswer;
    private String answer;
    private Examination[] questions=new Examination[]{
            new Examination("1.中国共产党和各民主党派共同协商、管理国家事务的合作方式主要包括(  )等几种",
                    "A.中国人民政治协商会议中国共产党召集的协商座谈会共同 参加国家政权" ,
                    "B.全国人民代表大会:中国人民政治协商会议共同参加国家政权" ,
                    "C.中国人民政治协商会议全国人民代表大会 :中国共产党召集的协商座谈会" ,
                    "D.全国人民代表大会 共同参加国家政权 中国共产党召集的协商座;谈会","A"),
            new Examination("2.以下哪部党章是我们党历史上唯一-一 次不是由党的代表大会通过的党章。( )",
                    "A.《中国共产党纲领》" ,
                    "B.《中国共产党第一 -次修正章程》" ,
                    "C.《中国共产党第二次修正章程决案》",
                    "D.《中国 共产党第三次修正章程决案》","D"),
            new Examination("3.十七大党章中党的基本路线的新表述增加的内容是: ()",
                    "A.富强",
                    "B.和谐",
                    "C.民主",
                    "D.文明","B"),
            new Examination("4.党员的先进性，党员与一般群众的根本区别，就在于党员具有高度的( )。",
                    "A.组织纪律性" ,
                    "B.共产主义觉悟",
                    "C.历史使命感与责任感",
                    "D.全心全意为人民服务的品质","B"),
            new Examination("5.党的先进性是: ( )",
                    "A.具体的历史的",
                    "B.一成不变的",
                    "C.抽象的",
                    "D.随着时间的推移变化的","A"),
            new Examination("6.党的十七大党章提出党要适应改革开放和社会主义现代化建设的要求，坚持(  )加强和改善党的领导。",
                    "A.党的基本路线",
                    "B.党的宗旨",
                    "C.科学执政、民主执政、依法执政",
                    "D.民主集中制","C"),
            new Examination("7.党的民主集中制的四个服从是指:个人服从组织,少数服从多数,(  )，全党服从中央。",
                    "A.党员服从干部",
                    "B.委员会服从代表大会",
                    "C.下级服从上级",
                    "D.地方各级委员会服从中央委员会","C")

    };

    private Examination[] questions2=new Examination[]{
            new Examination("1、“三个代表”重要思想的精髓是（ ）。",
                    "A、解放思想，实事求是，与时俱进" ,
                    "B、解放思想，实事求是" ,
                    "C、解放思想" ,
                    "D、实事求是,与时俱进","A"),
            new Examination("2、下列属于正确的入党动机有（ ）。",
                    "A、入党可以得到党组织的信任和重用" ,
                    "B、入党可以受到尊重" ,
                    "C、入党可以找到好工作",
                    "D、入党可以得到更快的进步","D"),
            new Examination("3、党员的党龄，从（ ）之日算起。",
                    "A、递交入党志愿书",
                    "B、预备期满转为正式党员",
                    "C、支部大会通过其为预备党员",
                    "D、从上党校开始","B"),
            new Examination("4、现阶段中国最大的国情是（ ）。",
                    "A、生产力水平低，经济发展落后" ,
                    "B、我国正处于并将长期处于社会主义初级阶段",
                    "C、社会主义市场经济不完善",
                    "D、社会主义中级阶段","B"),
            new Examination("5、党的纪律处分有哪几种？（ ）",
                    "A、警告、严重警告、撤销党内职务、留党察看、开除党籍",
                    "B、警告、撤销党内职务、开除党籍",
                    "C、警告、严重警告、撤销职务、留党察看、开除党籍",
                    "D、警告、撤销职务、留党察看、开除党籍","A"),
            new Examination("6、中国共产党党员必须履行的第一条义务的首条是（ ）",
                    "A、学习党的基本知识，学习科学、文化和业务知识",
                    "B、学习党的路线、方针、政策及决议",
                    "C、认真学习马克思列宁主义、毛泽东思想、邓小平理论和“三个代表”重要思想",
                    "D、努力提高为人民服务的本领。","C"),
            new Examination("7、在党的十七大报告中，胡锦涛总书记对科学发展观作了全面而又深刻阐述，他指出科学发展观的核心是。（C）\n",
                    "A、发展",
                    "B、统筹兼顾",
                    "C、以人为本",
                    "D、全面协调可持续","C")

    };

    private Examination[] questions3=new Examination[]{
            new Examination("1.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，国内生产总值达到99.1万亿元，增长________。城镇新增就业1352万人，调查失业率在________以下。",
                    "A.6.1%  5.3%" ,
                    "B.6.1%  3.5%" ,
                    "C.6.2%  3.5%" ,
                    "D.6.2%  5.3%","A"),
            new Examination("2.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，社会消费品零售总额超过________，消费持续发挥主要拉动作用。",
                    "A. 20万亿元" ,
                    "B. 30万亿元" ,
                    "C. 35万亿元",
                    "D. 40万亿元","D"),
            new Examination("3.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，减税降费________万亿元，超过原定的近2万亿元规模，制造业和小微企业受益最多。",
                    "A. 2.28",
                    "B. 2.36",
                    "C. 2.54",
                    "D. 2.63","B"),
            new Examination("4.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，城镇保障房建设和农村危房改造深入推进。义务教育学生生活补助人数增加近________，高职院校扩招________人。",
                    "A. 30%  100万" ,
                    "B. 40%  100万",
                    "C. 30%  100万",
                    "D. 40%  120万","B"),
            new Examination("5.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，加强党风廉政建设，扎实开展________主题教育，严格落实中央八项规定精神，持续纠治“四风”，为基层松绑减负。",
                    "A.不忘初心、牢记使命",
                    "B.两学一做",
                    "C.三严三实",
                    "D.红色基因","A"),
            new Examination("6.)2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，新冠肺炎疫情发生后，党中央将疫情防控作为头等大事来抓，习近平总书记亲自指挥、亲自部署，坚持把________放在第一位。",
                    "A.人民生命安全和经济社会发展",
                    "B.控制疫情和经济社会发展",
                    "C.人民生命安全和身体健康",
                    "D.控制疫情和疫苗研发","C"),
            new Examination("7.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在回顾2019年和今年以来工作时指出，在疫情防控中，我们积极开展国际合作，本着________态度，及时通报疫情信息，主动分享防疫技术和做法，相互帮助、共同抗疫。",
                    "A.合作、透明、本土化",
                    "B.公开、公正、本土化",
                    "C.公开、透明、负责任",
                    "D.合作、共享、负责任","C")

    };

    private Examination[] questions4=new Examination[]{
            new Examination("1.2020年3月22日，在今日举行的国务院联防联控机制新闻发布会上表示，农业农村部将在全国范围展开农资打假“_____”行动。",
                    "A.春雷" ,
                    "B.夏雷" ,
                    "C.秋雷" ,
                    "D.冬雷","A"),
            new Examination("2.2020年3月21日，31个省区市报告新增确诊病例46例，其中45例为境外输入病例。唯一的本土新增病例来自_____，为首例境外输入关联本地病例。",
                    "A.山东省" ,
                    "B.北京市" ,
                    "C.上海市" ,
                    "D.广东省" ,"D"),
            new Examination("3.2020年3月26日，国家主席习近平在北京出席二十国集团领导人应对新冠肺炎特别峰会并发表题为《_____》的重要讲话。",
                    "A.为全球抗疫做出贡献",
                    "B.携手抗疫，共克时艰",
                    "C.抗疫，我们在行动",
                    "D.中国抗疫","B"),
            new Examination("4.全国总工会于3月12日正式开通了全国工会就业服务平台——“_____”。",
                    "A.工人就业服务号" ,
                    "B.工会就业服务号",
                    "C.工厂就业服务号",
                    "D.工会抗疫服务号","B"),
            new Examination("5.2020年3月16日，出版的第6期《求是》杂志将发表国家主席习近平的重要文章《为打赢疫情防控阻击战提供强大科技支撑》，文章强调，人类同疾病较量最有力的武器就是_____。",
                    "A.科学技术",
                    "B.医护人员",
                    "C.研究人员",
                    "D.器械装备","A"),
            new Examination("6.李克强主持召开国务院常务会议，会议指出，按照党中央、国务院决策部署，统筹推进疫情防控和经济社会发展，做好“六稳”工作，必须把_____放在首位。",
                    "A.稳金融",
                    "B.稳外贸",
                    "C.稳就业",
                    "D.稳投资","C"),
            new Examination("7.2020年3月1日出版的第5期《求是》杂志将发表中共中央总书记、国家主席、中央军委主席习近平的重要文章_______。",
                    "A.《全面提高依法防控依法治理能力，健全国家公共卫生应急管理体系》",
                    "B.《推动形成优势互补高质量发展的区域经济布局》",
                    "C.《关于坚持和发展中国特色社会主义的几个问题》",
                    "D.《坚定文化自信，建设社会主义文化强国》","C")

    };

    private Examination[] questions5=new Examination[]{
            new Examination("1.2020年3月8日引发的《美丽中国建设评估指标体系及实施方案》指出，将由第三方机构(中国科学院)对全国及31个省、自治区、直辖市开展美丽中国建设进程评估。以2020年为基年，以______年为周期开展____次评估。",
                    "A.5;2" ,
                    "B.5;1" ,
                    "C.4;2" ,
                    "D.5;3","A"),
            new Examination("2.2020年3月19日印发的《2020年网络扶贫工作要点》指出：明确到2020年底前，《网络扶贫行动计划》目标任务全面完成并巩固提升。网络覆盖质量进一步提升，全国行政村通光纤、通4G比例达到______，贫困村通宽带比例达到______。",
                    "A. 90%" ,
                    "B. 95%" ,
                    "C. 97%",
                    "D. 99%","D"),
            new Examination("3.2020年3月1日出版的第5期《求是》杂志发表习近平的重要文章《全面提高依法防控依法治理能力，健全国家公共卫生应急管理体系》。文章强调，要始终把人民群众生命安全和身体健康放在( )。",
                    "A.关键位置",
                    "B.第一位",
                    "C.核心位置",
                    "D.重要位置","B"),
            new Examination("4.2020年3月2日，习近平在北京考察新冠肺炎防控科研攻关工作时强调，人类同疾病较量最有力的武器就是( )。",
                    "A.医疗技术" ,
                    "B.科学技术",
                    "C.医疗条件",
                    "D.科学条件","B"),
            new Examination("5.2020年3月，国防部消息，我国决定把 ( )纳入国家安全体系，将尽快推动出台 ( ) 法。",
                    "A.生命安全",
                    "B.生物安全",
                    "C.科技安全",
                    "D.健康安全","A"),
            new Examination("6. 2020年3月2日，中央应对新冠肺炎疫情工作领导小组印发《当前春耕生产工作指南》。指南明确，要压实( )责任制。",
                    "A.粮食安全省长",
                    "B.菜篮子市长",
                    "C.粮食安全市长",
                    "D.菜篮子省长","C"),
            new Examination("7. 2020年3月，中共中央、国务院印发了《关于深化医疗保障制度改革的意见》，明确到2030年，全面建成以 ( )为主体，医疗救助为托底，补充医疗保险、商业健康保险、慈善捐赠、医疗互助共同发展的医疗保障制度体系。",
                    "A.医疗保险",
                    "B.大病医疗保险",
                    "C.基本医疗保险",
                    "D.核心医疗保险","C")

    };

    private Examination[] questions6=new Examination[]{
            new Examination("1.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，做好今年政府工作，要坚决贯彻党的基本理论、基本路线、基本方略，增强“四个意识”、坚定________、做到“两个维护”，紧扣________目标任务，统筹推进疫情防控和经济社会发展工作。",
                    "A.“四个自信”　全面建成小康社会" ,
                    "B.“四个自信”　全面打赢脱贫攻坚战" ,
                    "C. 反“四风”　全面建成小康社会" ,
                    "D. 反“四风”　全面打赢脱贫攻坚战","A"),
            new Examination("2.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，做好今年政府工作，要在疫情防控常态化前提下，坚持稳中求进工作总基调，坚持新发展理念，坚持以________为主线，坚持以________为动力推动高质量发展，坚决打好三大攻坚战，加大“六稳”工作力度。",
                    "A.供给侧结构性改革　改革" ,
                    "B.国家治理体系和治理能力改革　改革开放" ,
                    "C.国家治理体系和治理能力改革　改革",
                    "D.供给侧结构性改革　改革开放","D"),
            new Examination("3.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，今年要优先________，坚决打赢脱贫攻坚战，努力实现全面建成小康社会目标任务。",
                    "A.控疫情保民生",
                    "B.稳就业保民生",
                    "C.稳就业保经济",
                    "D.控疫情保经济","B"),
            new Examination("4.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告在阐述预期目标时指出，今年城镇登记失业率________左右;居民消费价格涨幅________左右。",
                    "A. 4.5%　3.5% " ,
                    "B. 5.5%　3.5%",
                    "C. 5.6%　3.7%",
                    "D. 5.5%　4.5%","B"),
            new Examination("5.)2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，我们没有提出全年经济增速具体目标，主要因为________不确定性很大，我国发展面临一些难以预料的影响因素。",
                    "A.全球疫情和经贸形势",
                    "B.全球疫情和经济发展",
                    "C.疫情防控和经贸形势",
                    "D.疫情防控和经济发展","A"),
            new Examination("6.2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，________是今年“六稳”工作的着力点。守住________底线，就能稳住经济基本盘。",
                    "A.民生",
                    "B.就业",
                    "C.“六保”",
                    "D.“七能”","C"),
            new Examination("7.)2020年5月22日，十三届全国人大三次会议开幕，李克强向大会作政府工作报告。报告指出，创新直达________的货币政策工具，务必推动企业便利获得贷款，推动利率持续下行。",
                    "A.基层",
                    "B.企业",
                    "C.实体经济",
                    "D.经济实体","C")
    };


    private int currentIndex=0;
    private boolean flag;
    private boolean[] flagArr;
    Intent intent;
    int testId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_activity);
        if(savedInstanceState!=null){
            currentIndex=savedInstanceState.getInt(KET_INDEX,0);
        }
        initView();
        intent=getIntent();
        testId=intent.getIntExtra("testId",1);
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Log.d(KET_INDEX,"onSaveInstanceState");
        outState.putInt(KET_INDEX,currentIndex);
    }

    //做题
    private void initView(){
        tvExit=(TextView)findViewById(R.id.tv_exam_exit);
        tvExit.setOnClickListener(this);

        tvQue=(TextView)findViewById(R.id.tv_exam_question1);
        tvQue.setOnClickListener(this);

        rgOption=(RadioGroup)findViewById(R.id.rg_exam_choose);
        rbOptionl = (RadioButton) findViewById(R.id.rb_exam_choose1);
        rbOption2 = (RadioButton) findViewById(R.id.rb_exam_choose2);
        rbOption3 = (RadioButton) findViewById(R.id.rb_exam_choose3);
        rbOption4 = (RadioButton) findViewById(R.id.rb_exam_choose4);

        answer=questions[currentIndex].getAnswer();
        flagArr=new boolean[questions.length];
        rgOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override  //对RadioGroup的监听
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_exam_choose1:
                        if(answer.equals("A")){
                            flag=true;
                        }
                        break;
                    case R.id.rb_exam_choose2:
                        if(answer.equals("B")){
                            flag=true;
                        }
                        break;
                    case R.id.rb_exam_choose3:
                        if(answer.equals("C")){
                            flag=true;
                        }
                        break;
                    case R.id.rb_exam_choose4:
                        if(answer.equals("D")){
                            flag=true;
                        }
                        break;
                }
            }
        });

        tvPre=(TextView)findViewById(R.id.tv_exam_previous);
        tvPre.setOnClickListener(this);

        tvNext=(TextView)findViewById(R.id.tv_exam_next);
        tvNext.setOnClickListener(this);

        tvAnswer=(TextView)findViewById(R.id.tv_exam_answer);
        tvAnswer.setText(answer);
    }

    ////处理按键
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //处理退出键
            case R.id.tv_exam_exit:
                if(tvAnswer.getVisibility()== View.GONE){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                            .setTitle("确定要退出测试吗")
                            .setPositiveButton("确定退出", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).setNegativeButton("继续测试", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    dialog.create().show();
                }else{
                    finish();
                }
                break;
                //点击题目实现跳转
            case R.id.tv_exam_question1:
                flagArr[currentIndex]=flag;
               // Log.e("ExamActivity",flagArr[currentIndex]+"");
                if(currentIndex==questions.length-1){
                    Intent intent=new Intent(ExamActivity.this, TestResultActivity.class);
                    intent.putExtra("totalNum",questions.length);
                    intent.putExtra("flag",flagArr);
                    startActivityForResult(intent,REQUEST_CODE_RESULT);
                }else{
                    currentIndex++;
                    updateQuestion();
                }
                break;
            case R.id.tv_exam_next:
                flagArr[currentIndex]=flag;
                //Log.e("ExamActivity", flagArr[currentIndex] + "num" + currentIndex);
                if(currentIndex==questions.length-1){
                    Intent intent = new Intent(ExamActivity.this, TestResultActivity.class);
                    intent.putExtra("totalNum",questions.length);
                    intent.putExtra("flag", flagArr);
                    startActivityForResult(intent, REQUEST_CODE_RESULT);
            }else{
                    currentIndex++;
                    answer = questions[currentIndex].getAnswer();
                    updateQuestion();
                }
                break;
            case R.id.tv_exam_previous:
                flagArr[currentIndex] = flag;
                //Log.e("ExamActivity", flagArr[currentIndex] + "");

                currentIndex--;
                answer = questions[currentIndex].getAnswer();

                updateQuestion();
                break;
            default:
                break;
        }
    }

    private void updateQuestion(){
        switch(testId){
            case 1:
                updateQuestion1();
                break;
            case 2:
                updateQuestion2();
                break;
            case 3:
                updateQuestion3();
                break;
            case 4:
                updateQuestion4();
                break;
            case 5:
                updateQuestion5();
                break;
            case 6:
                updateQuestion6();
                break;
        }
    }

    private void updateQuestion1(){
        //Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
        //tvAnswer.setText("答案："+questions[currentIndex].getAnswer()+explain[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

    private void updateQuestion2(){
//        Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions2[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions2[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions2[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions2[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions2[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
        //tvAnswer.setText("答案："+questions2[currentIndex].getAnswer()+explain2[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions2.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

    private void updateQuestion3(){
//        Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions3[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions3[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions3[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions3[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions3[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
        //tvAnswer.setText("答案："+questions3[currentIndex].getAnswer()+explain3[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions3.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

    private void updateQuestion4(){
//        Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions4[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions4[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions4[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions4[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions4[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
       // tvAnswer.setText("答案："+questions4[currentIndex].getAnswer()+explain4[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions4.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

    private void updateQuestion5(){
//        Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions5[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions5[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions5[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions5[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions5[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
       // tvAnswer.setText("答案："+questions5[currentIndex].getAnswer()+explain5[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions5.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

    private void updateQuestion6(){
//        Log.d(TAG, "Updating question text for question and option#" + currentIndex, new Exception());
        String question=questions6[currentIndex].getQuestion();
        tvQue.setText(question);
        String option1=questions6[currentIndex].getOption1();
        rbOptionl.setText(option1);
        String option2 =questions6[currentIndex].getOption2();
        rbOption2.setText(option2);
        String option3 = questions6[currentIndex].getOption3();
        rbOption3.setText(option3);
        String option4 = questions6[currentIndex].getOption4();
        rbOption4.setText(option4);

        rgOption.clearCheck();
        flag=false;
        tvAnswer.setVisibility(View.GONE);
        //tvAnswer.setText("答案："+questions6[currentIndex].getAnswer()+explain6[currentIndex]);

        if(currentIndex==0){
            tvPre.setText("没有前一题");
            tvPre.setEnabled(false);
        }else{
            tvPre.setText("前一题");
            tvPre.setEnabled(true);
        }

        if(currentIndex==questions6.length-1){
            tvNext.setText("完成测试");
        }
        tvNext.setText("下一个");
    }

}
