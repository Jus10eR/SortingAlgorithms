# Sorting Algorithms Visualization

This project is a graphical application that visualizes various sorting algorithms. The application allows users to select a sorting algorithm and see how it sorts an array of numbers in real-time.

## Features

- **Sorting Algorithms**: Supports Bubble Sort, Quick Sort, and Merge Sort.
- **Dynamic Visualization**: Visual representation of the sorting process.
- **User Controls**: Allows users to generate random arrays and adjust the size of the array.
- **Responsive UI**: The interface is responsive and updates in real-time as the sorting progresses.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or text editor of your choice

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/SortingAlgorithms.git
   ```
2. Open the project in your IDE.

### Running the Application

1. Navigate to the `BarGraphApp` class.
2. Run the `main` method to start the application.

### Usage

- **Select Algorithm**: Use the dropdown menu to select a sorting algorithm.
- **Start Sorting**: Click the "Start Sorting" button to begin the sorting process.
- **Generate Random Array**: Use the slider and text field to set the size of the array and click the "Generate Random Array" button to create a new array.

## Project Structure

- `ArrayManager.java`: Manages the array data and notifies listeners of changes.
- `SortingControlPanel.java`: Provides controls for selecting and starting sorting algorithms.
- `GraphPanel.java`: Visualizes the array and its sorting process.
- `BubbleSort.java`, `QuickSort.java`, `MergeSort.java`: Implementations of the sorting algorithms.
- `ArrayChangeListener.java`, `ArrayChangeAdapter.java`: Interfaces and adapters for listening to array changes.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.
