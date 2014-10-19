package test;

import java.util.Map;

import soot.Body;
import soot.BodyTransformer;
import soot.Local;
import soot.PackManager;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.jimple.Jimple;
import soot.options.Options;

public class AndroidInstrument {

	public static void main(String[] args) {
		System.out.println(AndroidInstrument.class.getResource("/").getPath());

		// prefer Android APK files// -src-prec apk
		Options.v().set_src_prec(Options.src_prec_apk);

		// output as APK, too//-f J
		Options.v().set_output_format(Options.output_format_dex);

		Options.v().set_force_overwrite(true);

		// resolve the PrintStream and System soot-classes
		Scene.v().addBasicClass("java.io.PrintStream", SootClass.SIGNATURES);
		Scene.v().addBasicClass("java.lang.System", SootClass.SIGNATURES);
		
		Scene.v().addBasicClass("android.widget.Toast", SootClass.SIGNATURES);
		Scene.v().addBasicClass("android.content.Context", SootClass.SIGNATURES);
		Scene.v().addBasicClass("android.content.ContextWrapper", SootClass.SIGNATURES);
		

		// PackManager.v().getPack("jtp").add(new
		// Transform("jtp.myInstrumenter", new AndroTransformer()));
		PackManager.v().getPack("jtp")
				.add(new Transform("jtp.instrumenter", new BodyTransformer() {

					@Override
					protected void internalTransform(Body b, String phaseName,
							Map<String, String> options) {
						// TODO Auto-generated method stub
						/*if (b.getMethod().getName().equals("onClick")) {
							System.out
									.println("--------------------------------");
							System.out.println(b.getMethod()
									.getDeclaringClass().getName());
						}*/

						if (b.getMethod()
								.getDeclaringClass()
								.getName()
								.equals("lux.android.staticanalysis.MainActivity$1")) {
							System.out.println("+===========================+");
							if(b.getMethod().getName().equals("onClick")) {
								//Local toast = Jimple.v().newLocal("toast", RefType.v("android.widget.Toast"));
								//System.out.println(toast.getType());
								SootClass toast = Scene.v().getSootClass("android.widget.Toast");
								
								System.out.println(toast.getMethods());
								SootMethod method = toast.getMethod("android.widget.Toast makeText(android.content.Context,int,int)");
							
								System.out.println(method);
								
								//Context android.content.ContextWrapper.getApplicationContext()
								//android.content.Context context;
								Local context = Jimple.v().newLocal("context", RefType.v("android.content.Context"));
								b.getLocals().add(context);
								Jimple.v().newStaticInvokeExpr(method.makeRef());

								//Jimple.v().newStaticInvokeExpr(method.makeRef(), args)
							}
						}
					}
				}));
		soot.Main.main(args);
	}
}
