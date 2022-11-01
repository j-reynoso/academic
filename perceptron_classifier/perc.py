import math
import random

import matplotlib.pyplot as plt
import numpy as np


class Neuron:

    def __init__(self, weights, patterns, desired_outputs, activation_type):
        self.weights = weights
        self.patterns = patterns
        self.desired_outputs = desired_outputs
        self.activation_type = activation_type

    def get_current_output(self, pattern):
        if self.activation_type == 'hard':
            current_output = self.get_net(pattern)
        elif self.activation_type == 'soft':
            current_output = self.funi(pattern)

        return current_output

    def learn(self, alpha):
        for ind, curr_pat in enumerate(self.patterns):
            if(self.activation_type == 'soft'):
                difference = self.desired_outputs[ind] - self.funi(curr_pat)
            elif(self.activation_type == 'hard'):
                difference = self.desired_outputs[ind] - self.sign_net(curr_pat)
            product = alpha * difference
            if difference != 0:
                for ind_w, w in enumerate(self.weights):
                    if ind_w == (len(self.weights) - 1):  # if we are at the bias weight
                        self.weights[ind_w] += (product * 1)  # always 1
                    else:
                        self.weights[ind_w] += (product * self.patterns[ind][ind_w])  # multiply input by weight

    def get_net(self, input_of_pattern):
        sum = 0
        for ind, i in enumerate(input_of_pattern):
            sum += i * self.weights[ind]
        sum += 1 * self.weights[-1]
        return sum

    def sign_net(self, input_of_pattern):
        if self.get_current_output(input_of_pattern) >= 0:
            return 1  # classifies as big car
        else:
            return 0  # does not activate neuron, is a small car

    def funi(self, input_of_pattern):
        k = 0.2
        return 1 / (1 + math.exp(-k * self.get_net(input_of_pattern)))

    def get_total_error(self):
        total_error = 0
        for ind, pat in enumerate(self.patterns):
            x, y = pat
            desired = self.desired_outputs[ind]
            # total error is the sum of (desired output of a pattern minus output of a pattern) squared
            if(self.activation_type == 'soft'):
                out = self.funi(pat)
            elif(self.activation_type == 'hard'):
                out = self.sign_net(pat)
            total_error += (desired - out) ** 2

        return total_error

    def test(self, test_patterns, test_desired_outputs):
        total_error = 0
        test_true_positives = 0
        test_true_negatives = 0
        test_false_positives = 0
        test_false_negatives = 0
        test_total = 0
        for ind, pat in enumerate(test_patterns):
            x, y = pat
            desired = test_desired_outputs[ind]
            if (self.get_net([x, y]) >= 0):
                actual = 1
            else:
                actual = 0
            if actual == desired:
                if desired == 1:
                    test_true_positives += 1
                elif desired == 0:
                    test_true_negatives += 1
            else:
                if desired == 1:
                    test_false_negatives += 1
                elif desired == 0:
                    test_false_positives += 1
            test_total += 1
            # total error is the sum of (desired output of a pattern minus output of a pattern) squared
            total_error += (desired - actual) ** 2

        print("Confusion Matrix:")
        header = ["Big Car", "Small Car"]
        print("\t", header[0].rjust(7), header[1].rjust(5))
        print(header[0], str(test_true_positives).rjust(7), str(test_false_negatives).rjust(5))
        print(header[1], str(test_false_positives).rjust(7), str(test_true_negatives).rjust(5))


def normalize_data(data):
    mins = np.min(data, axis=0)
    maxs = np.max(data, axis=0)
    for indr, row_obj in enumerate(data):
        for indc, value in enumerate(row_obj):
            if indc == 2:
                break
            # (xi – min(x)) / (max(x) – min(x))
            data[indr][indc] = ((value - mins[indc]) / (maxs[indc] - mins[indc]))
    return data


# takes in training sample size and testing sample size as decimals as well as dataset
# returns four arrays: training sample patterns, their desired outputs, testing sample patterns, their desired outputs
def train_and_test_data(training_size, testing_size, data):
    numbers = list(range(len(data)))
    training_data = []
    testing_data = []

    # selecting data randomly by creating list of possible indices
    # to use in dataset, add random one
    # .shuffle() was not random enough
    for i in range(int(training_size * len(data))):
        rand_int = random.choice(numbers)
        training_data.append(data[rand_int])
        numbers.remove(rand_int)

    train_patterns_arr = np.array(training_data)

    train_patterns = train_patterns_arr[:, [0, 1]]
    train_desired_outputs = train_patterns_arr[:, [2]]

    for i in range(int(testing_size * len(data))):
        rand_int = random.choice(numbers)
        testing_data.append(data[rand_int])
        numbers.remove(rand_int)

    test_patterns_arr = np.array(testing_data)
    test_patterns = test_patterns_arr[:, [0, 1]]
    test_desired_outputs = test_patterns_arr[:, [2]]

    return train_patterns, train_desired_outputs, test_patterns, test_desired_outputs


def plot(training_data, test_data, weights, title_1, title_2, activation_type):
    train_xs, train_ys = get_sublists(training_data)
    test_xs, test_ys = get_sublists(test_data)
    fx = np.linspace(0, 1)
    fy = (weights[0] * fx + weights[2]) / (-weights[1])
    equation = str(round(float(weights[0]), 4)) + "x + " + str(round(float(weights[1]), 4)) + "y + (" + str(
        round(float(weights[2]), 4)) + ")"
    figure, axis = plt.subplots(1, 2)
    figure.tight_layout()
    figure.subplots_adjust(top=0.75)

    axis[0].plot(train_xs, train_ys, '.')
    axis[0].set_title(title_1, pad=10)
    axis[1].plot(test_xs, test_ys, '.')
    axis[1].set_title(title_2, pad=10)
    axis[0].plot(fx, fy, '-r', label=equation)
    axis[1].plot(fx, fy, '-r', )

    for ax in axis:
        ax.set_ylim([-0.1, 1.1])
        ax.set_xlim([-0.1, 1.1])
        ax.set(xlabel='Cost Normalized (USD)', ylabel='Weight Normalized (Pounds)')
    plt.suptitle("Classification of car size based on weight and cost: \n" + activation_type + " activation",
                 fontsize=14, y=0.98)
    figure.legend(loc='center', bbox_to_anchor=(0.5, 0.85))
    plt.show()


def get_sublists(data):
    x = [el[0] for el in data]
    y = [el[1] for el in data]
    return x, y


def main():
    # Current Code:
    # generates model for Dataset B with training on 75% of dataset and testing on 25% of dataset
    # learning rate
    # alpha = 0.2
    alpha = 0.01
    # current dataset and total error goal of model
    data = np.genfromtxt('groupA.txt', delimiter=',')
    total_error_limit = .00001
    # data = np.genfromtxt('groupB.txt', delimiter=',')
    # total_error_limit = 40
    # data = np.genfromtxt('groupC.txt', delimiter=',')
    # total_error_limit = 700
    # training size in decimal
    training_size = 0.25
    # testing size in decimal
    testing_size = 0.75
    # number of inputs
    inputs_size = 2
    # iteration limit for learning
    iteration_limit = 5000
    # weights randomly initialized, add 1 to hold bias weight
    init_weights = []
    for i in range(inputs_size + 1):
        init_weights.append(random.uniform(-.5, .5))

    print(init_weights)

    data = normalize_data(data)
    # create subset datasets for training and testing
    training_data = []
    test_data = []
    train_patterns, train_outputs, test_patterns, test_outputs = train_and_test_data(training_size, testing_size, data)

    # create Neuron and pass initial weights, training data, and activation type
    neuron = Neuron(init_weights, train_patterns, train_outputs, 'hard')

    # initial total error of model with initial weights
    total_error = neuron.get_total_error()
    iterations = 0
    while total_error > total_error_limit and iterations < iteration_limit:
        neuron.learn(alpha)
        total_error = neuron.get_total_error()
        iterations += 1
        # print("total error is ", total_error, " at iteration ", iterations)

    # final total error once model is done learning
    print("Final Total Error after Training: ", total_error)
    print("Final Weights:")
    for i in neuron.weights:
        print(i)
    print("Iteration Count:", iterations)
    # test on final model
    neuron.test(test_patterns, test_outputs)

    # plotting, edit percentages and dataset letter to match input
    plot(train_patterns, test_patterns, neuron.weights, "training on 25% of dataset C",
         "testing on 75% of dataset C",
         neuron.activation_type)


main()
