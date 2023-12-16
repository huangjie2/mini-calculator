package com.kent;

/**
 * 计算器类（Calculator），可以实现两个数的加、减、乘、除运算，并可以进行undo和redo操作
 *
 */
import java.util.Stack;

class Calculator {
    private double currentValue = 0.0;
    private Stack<Operation> history = new Stack<>();
    private Stack<Operation> redoStack = new Stack<>();

    private abstract class Operation {
        protected double value;

        public Operation(double value) {
            this.value = value;
        }

        public abstract void apply();

        public abstract void undo();

        public abstract void redo();
    }

    private class AddOperation extends Operation {
        public AddOperation(double value) {
            super(value);
        }

        public void apply() {
            currentValue += value;
        }

        public void undo() {
            currentValue -= value;
        }

        public void redo() {
            apply();
        }
    }

    private class SubtractOperation extends Operation {
        public SubtractOperation(double value) {
            super(value);
        }

        public void apply() {
            currentValue -= value;
        }

        public void undo() {
            currentValue += value;
        }

        public void redo() {
            apply();
        }
    }

    private class MultiplyOperation extends Operation {
        public MultiplyOperation(double value) {
            super(value);
        }

        public void apply() {
            currentValue *= value;
        }

        public void undo() {
            currentValue /= value;
        }

        public void redo() {
            apply();
        }
    }

    private class DivideOperation extends Operation {
        public DivideOperation(double value) {
            super(value);
        }

        public void apply() {
            if (value != 0) {
                currentValue /= value;
            } else {
                throw new IllegalArgumentException("被除数不能为0!");
            }
        }

        public void undo() {
            currentValue *= value;
        }

        public void redo() {
            apply();
        }
    }

    public void add(double value) {
        AddOperation op = new AddOperation(value);
        op.apply();
        history.push(op);
        redoStack.clear();
    }

    public void subtract(double value) {
        SubtractOperation op = new SubtractOperation(value);
        op.apply();
        history.push(op);
        redoStack.clear();
    }

    public void multiply(double value) {
        MultiplyOperation op = new MultiplyOperation(value);
        op.apply();
        history.push(op);
        redoStack.clear();
    }

    public void divide(double value) {
        DivideOperation op = new DivideOperation(value);
        op.apply();
        history.push(op);
        redoStack.clear();
    }

    public void undo() {
        if (!history.isEmpty()) {
            Operation op = history.pop();
            op.undo();
            redoStack.push(op);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Operation op = redoStack.pop();
            op.redo();
            history.push(op);
        }
    }

    public double getCurrentValue() {
        return currentValue;
    }
}
