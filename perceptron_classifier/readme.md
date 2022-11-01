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

#### Hard Activation on Train 75, Test 25 on Data Set A using alpha = 0.01

Initial Weights: [0.40259504123764556, 0.004879915241741695, 0.325680496768307] <br>
Iterations to reach total error less than 0.00001: 5000+ <br>
Final total error: .002 <br>
Final Weights: [78.72793785, 69.71755564, -72.80264786] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 498 | 0 |
| __Predicted Small__ | 0 | 502 |

<img src="/perceptron_classifier/images/a/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set A using alpha = 0.01

Initial Weights: [-0.38373327008313396, 0.49010775675527285, 0.28460726326838026] <br>
Iterations to reach total error less than 0.00001: 5000+ <br>
Final total error: 0.004 <br>
Final Weights: [66.56573815, 60.1637666, -62.04863809] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1499 | 0 |
| __Predicted Small__ | 0 | 1501 |

<img src="/perceptron_classifier/images/a/hard25.png" width=50% height=50%>

#### Soft Activation on Train 75, Test 25 on Data Set A using alpha = 0.01 and k = 0.2

Initial Weights: [0.40259504123764556, 0.004879915241741695, 0.325680496768307] <br>
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

#### Hard Activation on Train 75, Test 25 on Data Set B using alpha = 0.01

Initial Weights:[0.10083533498483932, 0.1744219588453121, 0.3005531744971557] <br>
Iterations to reach total error less than 40: 3 <br>
Final total error: 33 <br>
Final Weights: [0.08438364, 0.08862261, -0.08944683] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 493 | 8 |
| __Predicted Small__ | 7 | 492 |

<img src="/perceptron_classifier/images/b/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set B using alpha =

Initial Weights: [0.43448586820591384, 0.032823614363672204, 0.11748703956935158] <br>
Iterations to reach total error less than 40: 2 <br>
Final total error: 37 <br>
Final Weights: [0.2936433, 0.04739429, -0.18251296] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1420 | 79 |
| __Predicted Small__ | 39 | 1462 |

<img src="/perceptron_classifier/images/b/hard25.png" width=50% height=50%>

#### Soft Activation on Train 75, Test 25 on Data Set B using alpha = 0.01 and k = 0.2

Initial Weights: [-0.3094728408329801, -0.46511407471085886, 0.04064627542651056] <br>
Iterations to reach total error less than 40: 254 <br>
Final total error: 39.9 <br>
Final Weights:[57.53522319, 64.71040425, -62.39612523] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 499 | 11 |
| __Predicted Small__ | 3 | 487 |

<img src="/perceptron_classifier/images/b/soft75.png" width=50% height=50%>

#### Soft Activation on Train 25, Test 75 on Data Set B using alpha = 0.01 and k = 0.2


Initial Weights: [0.0489605406698016, -0.2355154154674135, -0.4195846151783894] <br>
Iterations to reach total error less than 40: 115 <br>
Final total error: 39.9 <br>
Final Weights:[25.79683491, 30.6240997, -28.17248516] <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1469 | 13 |
| __Predicted Small__ | 25 | 1493 |

<img src="/perceptron_classifier/images/b/soft25.png" width=50% height=50%>

## Data Set C

#### Hard Activation on Train 75, Test 25 on Data Set C using alpha = 0.05

Initial Weights: [-0.12875100234657144, 0.4525349167579654, 0.23549170103489592] <br>
Iterations to reach total error less than 700: 5000+ <br>
Final total error: 1469 <br>
Final Weights: [0.2243126, 0.06383622, -0.2145083]  <br>

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 19 | 466 |
| __Predicted Small__ | 0 | 515 |

<img src="/perceptron_classifier/images/c/hard75.png" width=50% height=50%>

#### Hard Activation on Train 25, Test 75 on Data Set C using alpha = 0.05

Initial Weights: [0.27150482486026606, -0.2106683502269141, 0.3284160755819199] <br>
Iterations to reach total error less than 700: 2
Final total error: 352
Final Weights: [0.19991935, 0.09104083, -0.17158392]

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 562 | 935 |
| __Predicted Small__ | 105 | 1398 |

#### Soft Activation on Train 75, Test 25 on Data Set C using alpha = .05 and k = 0.2

Initial Weights: [-0.4457702678239681, -0.12438239065664392, -0.17786256340972317]
Iterations to reach total error less than 700: 65
Final total error: 699
Final Weights: [3.94907471, 2.52113654, -2.90643749]

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 441 | 57 |
| __Predicted Small__ | 228 | 274 |

<img src="/perceptron_classifier/images/c/soft75.png" width=50% height=50%>

#### Soft Activation on Train 25, Test 75 on Data Set C using alpha = 0.05 and k = 0.2

Initial Weights: [0.28739140747803826, 0.38938446298965357, 0.2862344173076594]
Iterations to reach total error less than 200: 89
Final total error: 199.99
Final Weights: [14.36777671, 9.98415727, -12.01696955]

|| Actual Big | Actual Small |
|----|----|----|
| __Predicted Big__ | 1120 | 387 |
| __Predicted Small__ | 437 | 1056 |

<img src="/perceptron_classifier/images/c/soft25.png" width=50% height=50%>



<img src="/perceptron_classifier/images/c/soft25.png" width=50% height=50%>
