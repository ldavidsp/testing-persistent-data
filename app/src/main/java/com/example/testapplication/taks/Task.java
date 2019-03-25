package com.example.testapplication.taks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.testapplication.taks.interfaces.OnCanceledListener;
import com.example.testapplication.taks.interfaces.OnCompleteListener;
import com.example.testapplication.taks.interfaces.OnFailureListener;
import com.example.testapplication.taks.interfaces.OnSuccessListener;

import java.util.concurrent.Executor;

public abstract class Task<TResult> {
    public Task() {
    }

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    public abstract boolean isCanceled();

    @Nullable
    public abstract TResult getResult();

    @Nullable
    public abstract <X extends Throwable> TResult getResult(@NonNull Class<X> var1) throws X;

    @Nullable
    public abstract Exception getException();

    @NonNull
    public abstract Task<TResult> addOnSuccessListener(@NonNull OnSuccessListener<? super TResult> var1);

    @NonNull
    public abstract Task<TResult> addOnFailureListener(@NonNull Executor var1, @NonNull OnFailureListener var2);

    @NonNull
    public Task<TResult> addOnCompleteListener(@NonNull OnCompleteListener<TResult> var1) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task<TResult> addOnCanceledListener(@NonNull OnCanceledListener var1) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }
}

