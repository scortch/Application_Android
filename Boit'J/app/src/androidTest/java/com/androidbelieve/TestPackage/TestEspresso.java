package com.androidbelieve.TestPackage;

/**
 * Created by clément on 01/04/2016.
 */
import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.DatePicker;

import com.androidbelieve.materialnavigationdrawer.ContactFragment;
import com.androidbelieve.materialnavigationdrawer.MainActivity;
import com.androidbelieve.materialnavigationdrawer.ArticleActivity;
import com.androidbelieve.materialnavigationdrawer.R;

import junit.framework.TestCase;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Date;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.core.deps.guava.base.CharMatcher.is;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;


public class TestEspresso extends ActivityInstrumentationTestCase2<ArticleActivity> {
    public TestEspresso() {
        super(ArticleActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Call the activity before each test.
        getActivity();
    }

    public void testNextActivity() {

        onView(withId(R.id.Titre)).check(matches(withText("123")));
    }

}