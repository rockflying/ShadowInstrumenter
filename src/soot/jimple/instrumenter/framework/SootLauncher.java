package soot.jimple.instrumenter.framework;

import java.util.Map;

import soot.SceneTransformer;

public class SootLauncher extends SceneTransformer{

	public SootLauncher(ShadowInstrumenter shadowTransformer) {
		// TODO Auto-generated constructor stub
	}

	public void launch(String apkPath, String androidJars)
	{
		String[] args = {};

		soot.Main.main(args);
	}
	
	@Override
	protected void internalTransform(String phaseName, Map<String, String> options) {
		// TODO Auto-generated method stub
		
	}

}
