# Perceptron Classifier on Different Data Sets
#### Perceptron Classifier:
The perceptron classifier aims to classify objects in a dataset using a linear combination of the values of the object and weights the model generates. It can also include a bias term that is added to the linear combination. This program implements hard and soft unipolar activation functions. The neuron object has three weights initialized randomly, the last acting as the bias. As it iterates through the training data set, the classifier readjusts these weights as it continues to learn the best line of seperation for the two classes. The classifier stops learning once it is under a specific total error depending on the data set. The classifier and its final weights are then tested on a portion of the data set to classify the car size. Sample output is below. 
#### The Data:
The data set contains two inputs for each car object and one class label: 
* the weight of a car in pounds
* the cost of the car in USD
* label of 1 indicates a large car, 0 indicates a small car.

##### Data Set A:
Data Set A has two distinct clusters of objects that form the two different classes with **no overlap**. 
<img src="/perceptron_classifier/images/a.png" width=50% height=50%>
##### Data Set B:
Data Set B has **more overlap** between the objects from the two different classes.
<img src="/perceptron_classifier/images/b.png" width=50% height=50%>
##### Data Set C:
Data Set C has the **most overlap** between the objects from the two different classes.
<img src="/perceptron_classifier/images/c.png" width=50% height=50%>

# Output: 
## Data Set A

#### Hard Activation on Train 75, Test 25 on Data Set A using alpha = 0.1

Initial Weights: 0.0, -2.0, -3.0 <br>
Iterations to reach total error less than 0.00001: 2 <br>
Final total error: 0 <br>
Final Weights: 1.71953259, -0.22767692, -0.8 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 481 | 0 |
| __Predicted Small__ | 0 | 519 |

<img src="/perceptron_classifier/images/a/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set A using alpha = 0.1

Initial Weights: 2.0, -1.0, -2.0 <br>
Iterations to reach total error less than 0.00001: 1 <br>
Final total error: 0 <br>
Final Weights: 2.62489372, -0.37990905, -1.2 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1499 | 0 |
| __Predicted Small__ | 0 | 1501 |

<img src="/perceptron_classifier/images/a/hard25.png" width=50% height=50%>

#### Soft Activation on Train 75, Test 25 on Data Set A using alpha = 0.5 and k = 0.2

Initial Weights: 0.0, 2.0, 5.0 <br>
Iterations to reach total error less than 0.00001: 3815 <br>
Final total error: 0.0000025 <br>
Final Weights: 119.84572867, 105.42027875, -110.88946878 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 497 | 0 |
| __Predicted Small__ | 0 | 503 |

<img src="/perceptron_classifier/images/a/soft75.png" width=50% height=50%>

#### Soft Activation on Train 25, Test 75 on Data Set A using alpha = 0.5 and k = 0.2

Initial Weights: -1.0, 4.0, -1.0 <br>
Iterations to reach total error less than 0.00001: 3915 <br>
Final total error: 0.0000099 <br>
Final Weights: 107.00118461, 95.30034722, -99.65177999 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1501 | 0 |
| __Predicted Small__ | 0 | 1499 |

<img src="/perceptron_classifier/images/a/soft25.png" width=50% height=50%>

## Data Set B

#### Hard Activation on Train 75, Test 25 on Data Set B using alpha = 

Initial Weights: -5.0, -3.0, 1.0 <br>
Iterations to reach total error less than 40: 1 <br>
Final total error: 37 <br>
Final Weights: 2.83372045, 3.18719123, -3 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 484 | 10 |
| __Predicted Small__ | 4 | 502 |

<img src="/perceptron_classifier/images/b/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set B using alpha =

Initial Weights: -5.0, 1.0, -5.0 <br>
Iterations to reach total error less than 40: 2 <br>
Final total error: 39 <br>
Final Weights: 2.08132913, 4.09922366, -3.5<br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1353 | 4 |
| __Predicted Small__ | 136 | 1507 |

<img src="/perceptron_classifier/images/b/hard25.png" width=50% height=50%>

#### Soft Activation on Train 75, Test 25 on Data Set B using alpha = 0.5 and k = 0.2

Initial Weights: 4.0, -1.0, 0.0 <br>
Iterations to reach total error less than 40: 8 <br>
Final total error: 38 <br>
Final Weights: 67.26706624, 74.16679735, -70.80983771 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 487 | 7 |
| __Predicted Small__ | 4 | 502 |

<img src="/perceptron_classifier/images/b/soft75.png" width=50% height=50%>

#### Soft Activation on Train 25, Test 75 on Data Set B using alpha = 0.5 and k = 0.2

Initial Weights: 2.0, 1.0, -3.0 <br>
Iterations to reach total error less than 40: 3 <br>
Final total error: 31 <br>
Final Weights: 31.63239059, 34.07116791, -33.15227424 <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1496 | 19 |
| __Predicted Small__ | 20 | 1465 |

<img src="/perceptron_classifier/images/b/soft25.png" width=50% height=50%>

## Data Set C

#### Hard Activation on Train 75, Test 25 on Data Set C using alpha = 

Initial Weights: -1.0, -3.0, -3.0 <br>
Iterations to reach total error less than 700: 7 <br>
Final total error: 697 <br>
Final Weights: 5.05939443, 1.37870506, -3.06666605  <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 416 | 173 |
| __Predicted Small__ | 98 | 313 |

<img src="/perceptron_classifier/images/c/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set C using alpha = and k =

Initial Weights: 
Iterations to reach total error less than 700:
Final total error:
Final Weights: 

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | TP | FP |
| __Predicted Small__ | FN | TN |

<img src="/perceptron_classifier/images/c/hard25.png" width=50% height=50%>

#### Soft Activation on Train 75, Test 25 on Data Set C using alpha = and k = 

Initial Weights: 
Iterations to reach total error less than 700:
Final total error:
Final Weights: 

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | TP | FP |
| __Predicted Small__ | FN | TN |

<img src="/perceptron_classifier/images/c/soft75.png" width=50% height=50%>

#### Soft Activation on Train 25, Test 75 on Data Set C using alpha = 0.5 and k = 0.2

Initial Weights: <br>
Iterations to reach total error less than 700:
Final total error:
Final Weights: 

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | TP | FP |
| __Predicted Small__ | FN | TN |

<img src="/perceptron_classifier/images/c/soft25.png" width=50% height=50%>
