package com.apps.testgrapg

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun LineGraph(
    xLabels: List<String>,
    yData: List<Float>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxWidth().height(300.dp),
        factory = { context ->
            val chart = LineChart(context)  // Initialise the chart
            val entries: List<Entry> = yData.indices.map { index -> Entry(index.toFloat(), yData[index]) }

            val dataSet = LineDataSet(entries,null)
            chart.data = LineData(dataSet)
            val xAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = IndexAxisValueFormatter(xLabels)

            val yAxis = chart.axisLeft
            yAxis.isEnabled = false
            chart.axisRight.setDrawGridLines(true)
            chart.xAxis.setDrawGridLines(false)
            chart.description.isEnabled = false
            chart.invalidate()
            chart
        }
    )
}
