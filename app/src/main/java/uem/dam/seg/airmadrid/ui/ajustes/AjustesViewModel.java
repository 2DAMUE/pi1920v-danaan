package uem.dam.seg.airmadrid.ui.ajustes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AjustesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AjustesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquin van los ajustes");
    }

    public LiveData<String> getText() {
        return mText;
    }
}