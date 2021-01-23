package com.project.daicuongbachkhoa.menubar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.student.StudentInfo;
import com.project.daicuongbachkhoa.student.algebrastudent.OptionAlgebraStudent;
import com.project.daicuongbachkhoa.student.lawstudent.OptionLawStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.OptionPhysicsOneStudent;

public class MenuBar extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private LinearLayout btnGoLogOut;
    private LinearLayout btnGoAuthor;
    private LinearLayout btnGoTarget;
    private LinearLayout btnGoFeedback;
    private LinearLayout btnGoHome;
    private ImageView imgGoMenu, imgPhysicsOne, imgAlgebra, imgLaw;
    private ImageButton btnPhysic, btnLaw, btnAlgebla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);

        drawerLayout = findViewById(R.id.drawer_layout);
        btnGoHome = findViewById(R.id.btnGoHome);
        btnGoAuthor = findViewById(R.id.btnGoAuthor);
        btnGoFeedback = findViewById(R.id.btnGoFeedback);
        btnGoTarget = findViewById(R.id.btnGoTarget);
        btnGoLogOut = findViewById(R.id.btnGoLogOut);
        imgGoMenu = findViewById(R.id.imgGoMenu);

        imgPhysicsOne = findViewById(R.id.imgPhysicsOne);
        imgAlgebra = findViewById(R.id.imgAlgebra);
        imgLaw = findViewById(R.id.imgLaw);
        imgGoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMenu();
            }
        });
        btnGoLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutAccount();
            }
        });
        btnGoAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAuthor();
            }
        });
        btnGoTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTarget();
            }
        });
        btnGoFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFeedback();
            }
        });
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goInformationUser();
            }
        });
        imgPhysicsOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionPhysic();
            }
        });
        imgLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionLaw();
            }
        });
        imgAlgebra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goOptionAlgebra();
            }
        });
    }

    private void goOptionAlgebra() {
        startActivity(new Intent(MenuBar.this, OptionAlgebraStudent.class));
    }

    private void goOptionLaw() {
        startActivity(new Intent(MenuBar.this, OptionLawStudent.class));
    }

    private void goOptionPhysic() {
        startActivity(new Intent(MenuBar.this, OptionPhysicsOneStudent.class));
    }

    private void goInformationUser() {
        startActivity(new Intent(this, StudentInfo.class));
        //finish();
    }

    private void goMenu() {
        openDrawer(drawerLayout);
    }

    private void goFeedback() {
        startActivity(new Intent(MenuBar.this, Feedback.class));
        //finish();
    }

    private void goTarget() {
        startActivity(new Intent(MenuBar.this, Target.class));
       // finish();
    }

    private void goAuthor() {
        startActivity(new Intent(this, Author.class));
        //finish();
    }

    private void logOutAccount() {
        FirebaseAuth.getInstance().signOut();
        //finish();
        startActivity(new Intent(MenuBar.this, MainActivity.class));
        //moveTaskToBack(true);
        //android.os.Process.killProcess(android.os.Process.myPid());
        //System.exit(1);
        finish();
    }

 /*   public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }
*/

    // các hàm khoá mở navigation bar
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        // khoá
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // hàm thêm cho hình nền !
    public void clickLogo(View view) {
        closeDrawer(drawerLayout);
        // hàm này bỏ đi cũng được, click vào logo trên thôi mà !
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    // thao tác khoá thanh bar
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}