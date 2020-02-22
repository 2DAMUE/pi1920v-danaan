package uem.dam.seg.airmadrid.ui.redes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RedesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RedesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquin van las redes socieles");
    }

    public LiveData<String> getText() {
        return mText;
    }
}