package util;

public class UtilStackTrace {

	public static void writeStackTrace(){
		//スタックトレースを出力する
		Throwable t = new Throwable();
		System.out.println("/----------------------------------------");
		for(StackTraceElement e :t.getStackTrace()){
			System.out.printf("  %s.%s(%s:%d)\n",e.getClassName(),e.getMethodName(),e.getFileName(),e.getLineNumber());
		}
		System.out.println("----------------------------------------/");
	}
	
}
