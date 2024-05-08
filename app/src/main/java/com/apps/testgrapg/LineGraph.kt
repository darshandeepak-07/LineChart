package com.apps.testgrapg

import android.graphics.Color
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
import com.github.mikephil.charting.formatter.ValueFormatter

@Composable
fun LineGraph(
    xLabels: List<String>,
    yData: List<Float>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxWidth().height(300.dp),
        factory = { context ->
            val chart = LineChart(context)
            val entries: List<Entry> = yData.indices.map { index -> Entry(index.toFloat(), yData[index]) }

            val dataSet = LineDataSet(entries, null)
            dataSet.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "$$value"
                }
            }

            dataSet.circleRadius = 8f
            dataSet.circleHoleRadius = 0f
            dataSet.color = Color.RED
            dataSet.setCircleColor(Color.RED)
            dataSet.valueTextColor = Color.RED

            chart.data = LineData(dataSet)
            dataSet.valueTextSize = 14f
            val xAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawAxisLine(false)
            xAxis.valueFormatter = IndexAxisValueFormatter(xLabels)
            xAxis.textSize = 10f


            val minimumValueX = -0.2f
            val maximumValueX = xLabels.size.toFloat() - 0.8f

            xAxis.axisMaximum = maximumValueX
            xAxis.axisMinimum = minimumValueX
            xAxis.labelCount = xLabels.size
            xAxis.granularity = 1f
            xAxis.isGranularityEnabled = true
          xAxis.setCenterAxisLabels(false)
            xAxis.labelRotationAngle = 0f


            val yAxis = chart.axisLeft
            yAxis.isEnabled = false
            val yAxisRight = chart.axisRight
            yAxisRight.textSize = 13f
            yAxisRight.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return "$$value"
                }
            }
            chart.axisRight.setDrawAxisLine(false)
            chart.axisRight.setDrawGridLines(true)
            chart.xAxis.setDrawGridLines(false)
            chart.description.isEnabled = false
            chart.legend.isEnabled = false

            chart.invalidate()
            chart
        }
    )
}
