package rx.android.samples;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] result = new String[1];

                Subscription subscription = getImageNetworkCall()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onCompleted() {
                                // Update user interface if needed
                                Log.d("completed","yes");
                                Log.d("result",result[0]);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("error",e.getMessage());

                            }

                            @Override
                            public void onNext(String bitmap) {
                                Log.d("bit",bitmap);
                                result[0] =bitmap;
                                // Handle result of network request
                            }
                        });
                subscription.unsubscribe();
            }

            //multiple calls
            /*findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String[] result = new String[1];
                    final ArrayList<String> res = new ArrayList<String>();

                    Subscription subscription = Observable.merge(getImageNetworkCall(),getImageNetworkCall2())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {

                                @Override
                                public void onCompleted() {
                                    // Update user interface if needed
                                    Log.d("completed","yes");
                                    for(int i =0; i<res.size();i++){
                                        Log.d("result",res.get(i));
                                    }

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.d("error",e.getMessage());

                                }


                                @Override
                                public void onNext(String bitmap) {
                                    Log.d("bit",bitmap);
                                    res.add(bitmap);
                                    // Handle result of network request
                                }
                            });
                }*/
        });

    }

    public Observable<String> getImageNetworkCall() {
        // Insert network call here!
        return Observable.just("we");
    }

    public Observable<String> getImageNetworkCall2() {
        // Insert network call here!
        return Observable.just("we");
    }



}
