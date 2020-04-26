package com.senior.courseselectingsystem.student;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.senior.courseselectingsystem.R;
import com.senior.courseselectingsystem.model.Course;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Course";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Course mCourse;
    private TextView mName, mTeacher, mDescrip, mChosen, mUplimit;
    private Button mButton;

    private String istaken;


    private OnFragmentInteractionListener mListener;

    public CourseDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailFragment newInstance(String param1, String param2) {
        CourseDetailFragment fragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCourse = getArguments().getParcelable("Course");
            istaken = getArguments().getString("istaken");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_course_detail, container, false);
        mName = view.findViewById(R.id.fg_detail_name);
        mTeacher = view.findViewById(R.id.fg_detail_teacher);
        mDescrip = view.findViewById(R.id.fg_detail_descrip);
        mChosen = view.findViewById(R.id.fg_detail_chosen);
        mUplimit = view.findViewById(R.id.fg_detail_uplimit);

        mName.setText(mCourse.getName());
        mTeacher.setText(mCourse.getTeacher());
        mDescrip.setText(mCourse.getDescrip());
        mChosen.setText(mCourse.getChosen()+"");
        mUplimit.setText(mCourse.getUplimit()+"");

        mButton = (Button) view.findViewById(R.id.fg_detail_btn);
        if(istaken.equals("taken")){
            mButton.setText("已选择该课程");
            mButton.setTextColor(0xFFFFFFFF);
            mButton.setEnabled(false);

        }else{
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonPressed(mCourse.getNum());
                }
            });
        }


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String course) {
        if (mListener != null) {
            mListener.onFragmentInteraction(course);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String course);
    }
}
