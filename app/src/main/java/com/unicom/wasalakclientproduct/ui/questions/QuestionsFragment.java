package com.unicom.wasalakclientproduct.ui.questions;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.FragmentQuestionsBinding;
import com.unicom.wasalakclientproduct.model.questions.Question;
import com.unicom.wasalakclientproduct.model.questions.QuestionAdapter;

import java.util.ArrayList;

public class QuestionsFragment extends Fragment {

    //region Members
    private String TAG = this.getClass().getSimpleName();
    private FragmentQuestionsBinding binding;
    private NavController navController;
    private QuestionAdapter questionAdapter;
    private ArrayList<Question> questions;
    //endregion
    //region Events


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (navController == null) {
            navController = Navigation.findNavController(view);
        }

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigateUp();
            }
        });

        loadQuestionData();

        binding.refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadQuestionData();
                binding.refresh.setRefreshing(false);
            }
        });
    }

    //endregion

    private void loadQuestionData() {
        questionAdapter = new QuestionAdapter();
        questions = new ArrayList<>();
        questions.add(new Question(0, getString(R.string.question1), getString(R.string.answer1)));
        questions.add(new Question(1, getString(R.string.question2), getString(R.string.answer2)));
        questions.add(new Question(2, getString(R.string.question3), getString(R.string.answer3)));
        questions.add(new Question(3, getString(R.string.question4), getString(R.string.answer4)));
        questions.add(new Question(4, getString(R.string.question5), getString(R.string.answer5)));
        questionAdapter.submitList(questions);
        binding.rvQuestions.setAdapter(questionAdapter);


    }
    //regionHelper Functions
    //endregion


}