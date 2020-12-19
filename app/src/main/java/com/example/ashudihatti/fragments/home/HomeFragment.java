package com.example.ashudihatti.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.ashudihatti.R;
import com.example.ashudihatti.adapter.home_adapter;
import com.example.ashudihatti.info.home_info;
import com.example.ashudihatti.product;
import com.example.ashudihatti.searchView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    ImageSlider slider;
    ImageView cart;
    ShimmerFrameLayout shimmer1,shimmer2,shimmer3,shimmer4,shimmer5;
    LinearLayout shimmer_linear_layout,Men,Cosmetic,Women;
    RecyclerView new_arrival_recyclerview,best_seller__recyclerview,you_may_like_recyclerview;
    TextView home_search_view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

      View root = inflater.inflate(R.layout.fragment_home, container, false);

        //Image slider Id
        slider = root.findViewById(R.id.slider);
        //ImageView Id
        cart = root.findViewById(R.id.home_cart);
        //TextView Id
        home_search_view = root.findViewById(R.id.home_search_view);
        //Linear layout Id
        shimmer_linear_layout = root.findViewById(R.id.shimmer_linear_layout);
        Men = root.findViewById(R.id.Men);
        Cosmetic = root.findViewById(R.id.Cosmetic);
        Women = root.findViewById(R.id.Women);
        //recyclerview Id
        new_arrival_recyclerview = root.findViewById(R.id.new_arrival_recyclerview);
        best_seller__recyclerview = root.findViewById(R.id.best_seller__recyclerview);
        you_may_like_recyclerview = root.findViewById(R.id.you_may_like_recyclerview);

        //shimmer animation
        shimmer1 = root.findViewById(R.id.shimmer1);
        shimmer2 = root.findViewById(R.id.shimmer2);
        shimmer3 = root.findViewById(R.id.shimmer3);
        shimmer4 = root.findViewById(R.id.shimmer4);
        shimmer5 = root.findViewById(R.id.shimmer5);

        shimmer1.startShimmer();
        shimmer2.startShimmer();
        shimmer3.startShimmer();
        shimmer4.startShimmer();
        shimmer5.startShimmer();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmer_linear_layout.setVisibility(View.GONE);
                shimmer1.stopShimmer();
                shimmer2.stopShimmer();
                shimmer3.stopShimmer();
                shimmer4.stopShimmer();
                shimmer5.stopShimmer();
            }
        }, 2500);

        //Top Image Slider
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner1,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner2,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner3,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ashudihatti_banner4,ScaleTypes.FIT));
        slider.setImageList(slideModels, ScaleTypes.FIT);

        //On Click
                //ImageView
        cart.setOnClickListener(this);
                //TextView
        home_search_view.setOnClickListener(this);
                //Layout
        Men.setOnClickListener(this);
        Cosmetic.setOnClickListener(this);
        Women.setOnClickListener(this);

        //functions
        get_new_arrival();
        get_best_seller();
        get_you_may_like();

        return root;

    }

    public void get_new_arrival(){

        List<String> color = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<home_info> home_list = new ArrayList<>();

        color.add("red");
        color.add("pink");
        color.add("black");

        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/10940558/2020/11/12/0dfd6a1e-bd34-4398-a9c5-dd0baf1494911605174755890-Roadster-Men-Tshirts-1501605174753715-1.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/10940558/2020/11/12/b48c09c5-e655-4cf8-b247-9bcbb38292371605174755785-Roadster-Men-Tshirts-1501605174753715-3.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/10940558/2020/11/12/572bdca5-11a7-4c9f-b83c-99a5d241e06b1605174755736-Roadster-Men-Tshirts-1501605174753715-4.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/10940558/2020/11/12/0b80b215-6c84-467b-8a72-301f2a517b211605174755687-Roadster-Men-Tshirts-1501605174753715-5.jpg");


        home_list.add(new home_info("1","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","3.5","100",images));
        home_list.add(new home_info("2","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","4.5","100",images));
        home_list.add(new home_info("3","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","5","100",images));
        home_list.add(new home_info("4","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","2","100",images));
        home_list.add(new home_info("5","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","1.5","100",images));
        home_list.add(new home_info("6","product_name","1254","Men","Topwear","T-Shirts","sale",color,"899","584","new_arrival","Teal blue solid T-shirt, has a Henley neck, and long sleeves","Material: 100% cotton Machine Wash","2.1","100",images));


        home_adapter adapter = new home_adapter(getContext(),home_list);
        new_arrival_recyclerview.setAdapter(adapter);

    }

    public void get_best_seller(){

        List<String> color = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<home_info> home_list = new ArrayList<>();

        color.add("red");
        color.add("pink");
        color.add("black");

        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/2948543/2018/3/30/11522394099790-HIGHLANDER-Men-Black-Slim-Fit-Mid-Rise-Clean-Look-Stretchable-Jeans-5171522394099590-1.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/2948543/2018/3/30/11522394099764-HIGHLANDER-Men-Black-Slim-Fit-Mid-Rise-Clean-Look-Stretchable-Jeans-5171522394099590-2.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/2948543/2018/3/30/11522394099743-HIGHLANDER-Men-Black-Slim-Fit-Mid-Rise-Clean-Look-Stretchable-Jeans-5171522394099590-3.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/2948543/2018/3/30/11522394099721-HIGHLANDER-Men-Black-Slim-Fit-Mid-Rise-Clean-Look-Stretchable-Jeans-5171522394099590-4.jpg");


        home_list.add(new home_info("1","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","3","100",images));
        home_list.add(new home_info("2","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","5","100",images));
        home_list.add(new home_info("3","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","2","100",images));
        home_list.add(new home_info("4","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","4","100",images));
        home_list.add(new home_info("5","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","1","100",images));
        home_list.add(new home_info("6","HIGHLANDER","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","2","100",images));


        home_adapter adapter = new home_adapter(getContext(),home_list);
        best_seller__recyclerview.setAdapter(adapter);

    }

    public void get_you_may_like(){

        List<String> color = new ArrayList<>();
        List<String> images = new ArrayList<>();
        List<home_info> home_list = new ArrayList<>();

        color.add("red");
        color.add("pink");
        color.add("black");

        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/8070191/2018/12/4/33f068ee-2621-4dc7-80b0-ca7437dba7981543914504444-Cotton-Dhoti-White-Gold-Jari-4671543914503347-1.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/8070191/2018/12/4/e7f57088-5726-42f4-9403-992e6fa6c2241543914504429-Cotton-Dhoti-White-Gold-Jari-4671543914503347-2.jpg");
        images.add("https://assets.myntassets.com/h_720,q_90,w_540/v1/assets/images/8070191/2018/12/4/889cce0d-f1b3-4324-a7c0-c72b339ce43f1543914504415-Cotton-Dhoti-White-Gold-Jari-4671543914503347-3.jpg");

        home_list.add(new home_info("1","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","3","100",images));
        home_list.add(new home_info("2","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","5","100",images));
        home_list.add(new home_info("3","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","2","100",images));
        home_list.add(new home_info("4","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","4","100",images));
        home_list.add(new home_info("5","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","1","100",images));
        home_list.add(new home_info("6","RAMRAJ COTTON","1254","Men","Bottomwear","Jeans","sale",color,"1894","904","best_seller","Men Black Slim Fit Mid-Rise Clean Look Stretchable Cropped Jeans","Material: 100% cotton Machine Wash","2","100",images));

        home_adapter adapter = new home_adapter(getContext(),home_list);
        you_may_like_recyclerview.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Men){
            Intent intent = new Intent(getActivity(), product.class);
            intent.putExtra("heading","Men");
            startActivity(intent);
        }else if(view.getId() == R.id.Cosmetic){
            Intent intent = new Intent(getActivity(),product.class);
            intent.putExtra("heading","Cosmetic");
            startActivity(intent);
        }else if(view.getId() == R.id.Women){
            Intent intent = new Intent(getActivity(),product.class);
            intent.putExtra("heading","Women");
            startActivity(intent);
        }else if(view.getId() == R.id.home_search_view){
            startActivity(new Intent(getActivity(), searchView.class));
        }else if(view.getId() == R.id.home_cart){
            startActivity(new Intent(getActivity(), com.example.ashudihatti.cart.class));
        }
    }
}