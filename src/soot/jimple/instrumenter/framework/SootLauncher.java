package soot.jimple.instrumenter.framework;

import java.util.Map;

import soot.G;
import soot.SceneTransformer;
import soot.options.Options;

public class SootLauncher extends SceneTransformer{

	public SootLauncher(ShadowInstrumenter shadowTransformer) {
		// TODO Auto-generated constructor stub
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

		soot.Main.main(args);
	}
	
	@Override
	protected void internalTransform(String phaseName, Map<String, String> options) {
		// TODO Auto-generated method stub
		
	}

}
