package soot.jimple.instrumenter.framework;

import java.util.Map;

import soot.G;
import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;
import soot.options.Options;

public class SootLauncher extends SceneTransformer{
	
	private ShadowInstrumenter instrumenter;

	public SootLauncher(ShadowInstrumenter shadowTransformer) {
		// TODO Auto-generated constructor stub
		this.instrumenter = shadowTransformer;
	}

	public void launch(String apkPath, String androidJars)
	{
		String[] args = {
	            "-android-jars", androidJars,
	            "-process-dir", apkPath,
	            "-ire",
	            "-pp",
	            "-allow-phantom-refs",
	            "-w",
				"-p", "cg", "enabled:true"
	        };
			
		G.reset();

		Options.v().set_src_prec(Options.src_prec_apk);
		Options.v().set_output_format(Options.output_format_dex);

		PackManager.v().getPack("wjtp").add(new Transform("wjtp.apkpler", 
				new SootLauncher(instrumenter)));
		
		soot.Main.main(args);
	}
	
	@Override
	protected void internalTransform(String phaseName, Map<String, String> options) {
		// TODO Auto-generated method stub
		
	}

}
