package com.play.library_base.moduleinterface;

/**
 * 跨module调用方法
 * <p>
 * 各自module实现自己的跨module类即可
 * <p>
 */
public class ModuleMaster implements IModuleMaster {


    private IModuleMaster module;

    private static ModuleMaster mInstance;

    private ModuleMaster() {
    }

    public static ModuleMaster getInstance() {
        if (mInstance == null) {
            mInstance = new ModuleMaster();
        }
        return mInstance;
    }

    public IModuleMaster getModuleMaster() {
        return module;
    }

    public void setModuleMaster(IModuleMaster module) {
        this.module = module;
    }

    @Override
    public ModuleNativeModule getModuleNativeModule() {
        if (module == null) {
            return null;
        }
        return module.getModuleNativeModule();
    }
}
