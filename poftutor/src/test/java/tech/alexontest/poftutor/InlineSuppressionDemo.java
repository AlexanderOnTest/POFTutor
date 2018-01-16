package tech.alexontest.poftutor;

public class InlineSuppressionDemo {
    // This method signature has issues --> (line 5) warnings for LineLength and ParameterNumber expected
    public void hello(final int a, final int b, final int c, final int d, final int e, final int f, final int g, final int h) {
        System.out.println("Hello World!");
    }

    // All checks disabled between the comments --> (line 11) no warnings expected
    // CHECKSTYLE_OFF
    public void hello2(final int a, final int b, final int c, final int d, final int e, final int f, final int g, final int h) {
        System.out.println("Hello World!");
    }
    // CHECKSTYLE_ON

    // Only a single check disabled between the comments --> (line 18) warning for LineLength only expected
    // CSOFF: ParameterNumber
    public void hello3(final int a, final int b, final int c, final int d, final int e, final int f, final int g, final int h) {
        // CSON: ParameterNumber
        System.out.println("Hello World!");
    }

    // All checks back in operation --> (line 24) warnings for LineLength and ParameterNumber expected
    public void hello4(final int a, final int b, final int c, final int d, final int e, final int f, final int g, final int h) {
        System.out.println("Hello World!");
    }
}
