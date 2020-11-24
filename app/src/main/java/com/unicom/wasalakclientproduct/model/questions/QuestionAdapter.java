package com.unicom.wasalakclientproduct.model.questions;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.unicom.wasalakclientproduct.R;
import com.unicom.wasalakclientproduct.databinding.LayoutQuestionRowBinding;
import com.unicom.wasalakclientproduct.databinding.LayoutQuntityRowBinding;
import com.unicom.wasalakclientproduct.databinding.QuestionsListRowBinding;
import com.unicom.wasalakclientproduct.model.category.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionAdapter extends ListAdapter<Question, QuestionAdapter.QuestionViewHolder> {
    private ClickListener clickListener;
    LayoutInflater layoutInflater;

    public QuestionAdapter() {
        super(Question.itemCallback);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        QuestionsListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.questions_list_row, parent, false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        holder.binding.setQuestion(getItem(position));
        holder.binding.executePendingBindings();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final QuestionsListRowBinding binding;

        public QuestionViewHolder(final QuestionsListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question = getCurrentList().get(getAdapterPosition());
                    if (question.getClicked() == false) {
                        binding.tvQuestion.setBackgroundColor(Color.parseColor("#E7FFEF"));
                        binding.tvQuestion.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_collapse, 0,0, 0);
                        binding.tvAnswer.setVisibility(View.VISIBLE);
                        binding.seperator.setVisibility(View.VISIBLE);
                        question.setClicked(true);
                    } else {
                        question.setClicked(false);
                        binding.tvQuestion.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        binding.tvQuestion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_expand, 0,0 , 0);
                        binding.tvAnswer.setVisibility(View.GONE);
                        binding.seperator.setVisibility(View.GONE);
                    }


                }
            });
        }

    }


    public interface ClickListener {
        void onClickQuestion(int position);
    }
}
