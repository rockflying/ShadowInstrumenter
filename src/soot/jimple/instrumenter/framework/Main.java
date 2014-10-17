package soot.jimple.instrumenter.framework;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String apkPath = args[0];
		String androidJars = args[1];
		
		ShadowInstrumenter shadowTransformer = new ShadowInstrumenter();
		
		SootLauncher launcher = new SootLauncher(shadowTransformer);
		launcher.launch(apkPath, androidJars);
	}

}
