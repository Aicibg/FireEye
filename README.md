# 轻量级输入校验库 - Fire Eye

FireEye 2.0 在 1.0 的基础上，全部重写了代码，并优化了架构，性能上和逻辑上都大大提升。

---
轻量级简单易用的Android校验库。

这是一个简单Android校验库，按配置来验证用户输入的表单信息。
只需要几行代码，即可验证用户输入，并且将验证错误反馈给用户。
它内置了大量常用的验证类型，足以满足你的功能需求。

![截图](http://i.imgur.com/sucjaqE.png)

## Gradle 依赖

Add dependency

    dependencies {
        compile 'com.github.yoojia:fire-eye:2.0@aar'
    }

Maven

    <dependency>
        <groupId>com.github.yoojia</groupId>
        <artifactId>fire-eye</artifactId>
        <version>2.0</version>
        <type>aar</type>
    </dependency>

## 已内置支持的校验方式

### 静态模式 - StaticPattern

静态模式是指对输入内容进行模式匹配，不需要额外参数即可校验的模式。如校验邮件地址是否正确等。

* **Required** 必填选项
* **NotBlank** 非空数据
* **Digits** 仅数字
* **Email** 电子邮件
* **Numeric** 数值
* **BankCard** 信用卡号/银行卡号
* **Host** 主机地址
* **URL** Http URL
* **IPv4** IPv4地
* **Mobile** 中国的手机号码
* **VehicleNumber** 中国的民用车辆号牌
* **IDCard** 中国的身份证号（15位和18位）

### 数值模式 - ValuePattern

数值模式是指需要额外参数来完成对输入内容的校验过程的模式。如判断内容是否与另一个相同等。

* **EqualTo** 与指定值相同
* **RangeLength** 指定长度范围
* **MinLength** 最小长度
* **MaxLength** 最大长度
* **RangeValue** 最值范围
* **MinValue** 最小值
* **MaxValue** 最大值



## How to use - 如何使用

#### 对表单内各个EditText绑定其校验配置

    // 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框。
    MessageDisplay messageDisplay = new MessageDisplay() {
        @Override
        public void dismiss(TextView field) {
            field.setError(null);
        }

        @Override
        public void show(TextView field, String message) {
            field.setError(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    // 使用表单查找器来查找输入框
    View formView = findViewById(R.id.form);
    Form form = new Form(formView);

    FireEye fireEye = new FireEye();
    fireEye.add(form.byId(R.id.form_field_1), StaticPattern.Required.setMessage(R.string.tip_required), StaticPattern.Mobile);
    fireEye.add(form.byId(R.id.form_field_2), StaticPattern.BankCard.setMessage("请输入您的银行卡号"));

    fireEye.add(form.byId(R.id.form_field_3), StaticPattern.Digits);
    fireEye.add(form.byId(R.id.form_field_3), ValuePattern.MaxLength.setValue(20));

    fireEye.add(form.byId(R.id.form_field_4), StaticPattern.Required, StaticPattern.Email);
    fireEye.add(form.byId(R.id.form_field_5), ValuePattern.Required, ValuePattern.EqualsTo.lazy(new TextViewLoader(form.byId(R.id.form_field_4))));
    fireEye.add(form.byId(R.id.form_field_6), StaticPattern.Host);
    fireEye.add(form.byId(R.id.form_field_7), StaticPattern.URL);
    fireEye.add(form.byId(R.id.form_field_8), ValuePattern.MaxLength.setValue(5));
    fireEye.add(form.byId(R.id.form_field_9), ValuePattern.MinLength.setValue(4));
    fireEye.add(form.byId(R.id.form_field_10), ValuePattern.RangeLength.setFirstValue(4L).setSecondValue(8L));
    fireEye.add(form.byId(R.id.form_field_11), StaticPattern.NotBlank);
    fireEye.add(form.byId(R.id.form_field_12), StaticPattern.Numeric);
    fireEye.add(form.byId(R.id.form_field_13), ValuePattern.MaxValue.setValue(100));
    fireEye.add(form.byId(R.id.form_field_14), ValuePattern.MinValue.setValue(20));
    fireEye.add(form.byId(R.id.form_field_15), ValuePattern.RangeValue.setFirstValue(18L).setSecondValue(30L));

    Result r = eye.test();

    if(r.passed){
        // 校验通过
    }else{
        // 校验失败
    }

## 有用的接口 

### Debug

> FireEyeEnv.isDebug = true;

设置FireEye环境变量，可以查看FireEye的校验过程及结果。

### DUMP

> FireEye.dump() 
    
 此方法可以输出详细的校验配置信息。其输出内容示例：
 
    android.support.v7.internal.widget.TintEditText{42a99d60 VFED..CL .F...... 0,0-1080,118 #7f090040 app:id/form_field_1}@必填选项|手机号码:
    -> patterns:
    {pattern=Required, messageId=-1, message='请填写您的手机号'} ,
    {pattern=Mobile, messageId=-1, message='手机号错误'}
    
可以看到FireEye对每个输入框的配置情况。


## TODO

1. 自定义校验接口；