package com.example.course.unit2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

class TipApp {

    @Preview(showBackground = true)
    @Composable
    fun PreviewTip() {
        Tip(Modifier.fillMaxSize())
    }

    @Composable
    fun Tip(modifier: Modifier = Modifier) {
        Column(
            modifier = modifier
                .statusBarsPadding()
                .padding(horizontal = 40.dp)
                .safeDrawingPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Calculate Tip",
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 40.dp)
                    .align(alignment = Alignment.Start)
            )

            var billAmount: String by remember { mutableStateOf("") }
            BillAmountInput(ChangeableValue(billAmount) { billAmount = it })

            var tipPercent: Int by remember { mutableIntStateOf(15) }
            TipPercentSelector(ChangeableValue(tipPercent) { tipPercent = it })

            val tip = calculateTip(amount = billAmount.extractAmount() ?: 0.0, tipPercent = tipPercent)
            Text(
                text = "Tip: $tip",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }

    @Composable
    private fun BillAmountInput(billAmount: ChangeableValue<String>) {
        val amount = billAmount.value
        val isInputError = amount.isNotBlank() && amount.extractAmount() == null
        TextField(
            value = amount,
            onValueChange = billAmount.onChange,
            isError = isInputError,
            trailingIcon = { if (isInputError) Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            ) },
            label = { Text("Bill Amount") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun TipPercentSelector(tipPercentage: ChangeableValue<Int>) {
        val clickablePercentages = remember { mutableStateListOf(15, 20, 25) }
        var showDialog: Boolean by remember { mutableStateOf(false) }
        var justAdded: Boolean by remember { mutableStateOf(false) }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            val activeButtonColor = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            val inactiveButtonColor = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
            val selectColors = { p: Int -> if (tipPercentage.value == p) activeButtonColor else inactiveButtonColor }

            // render all tip buttons
            clickablePercentages.toSet().sorted().forEach {
                Button(
                    onClick = { tipPercentage.onChange(it) },
                    colors = selectColors(it),
                    contentPadding = PaddingValues()
                ) { Text("$it%") }
            }

            if (showDialog) {
                // controls to add new tip percentage
                AddTipPercent(
                    onNewTipPercentage = {
                        clickablePercentages.add(it)
                        showDialog = false
                        tipPercentage.onChange(it)
                    },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    requestFocus = justAdded
                )
                justAdded = false
            } else {
                // or button to show the controls
                IconButton(onClick = {
                    showDialog = true
                    justAdded = true
                }) {
                    Icon(Icons.Filled.Add, contentDescription = null)
                }
            }
        }
    }

    @Preview
    @Composable
    private fun AddTipPercentagePreview() {
        AddTipPercent({}, requestFocus = false)
    }

    @Composable
    private fun AddTipPercent(
        onNewTipPercentage: (Int) -> Unit,
        modifier: Modifier = Modifier,
        requestFocus: Boolean
    ) {
        var newPercentage: Int? by remember { mutableStateOf(null) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .background(Color.LightGray, shape = ButtonDefaults.shape)
                .padding(ButtonDefaults.ContentPadding)
        ) {
            val focusRequester = FocusRequester()
            BasicTextField(
                value = newPercentage?.toString() ?: "",
                onValueChange = { input -> newPercentage = input.toIntOrNull()?.let {
                    if (it > 99) 99 else it
                }},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .width(20.dp)
                    .background(Color.White)
                    .focusRequester(focusRequester)
            )
            if (requestFocus) {
                LaunchedEffect(Unit) { focusRequester.requestFocus() }
            }
            Text(
                text = "%",
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(Modifier.width(4.dp))
            IconButton(
                onClick = { onNewTipPercentage(newPercentage!!) },
                enabled = newPercentage != null,
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = null, tint = Color.White)
            }
        }
    }

    private fun String.extractAmount() = try { this.replace(',','.').toDouble() } catch (ex: NumberFormatException) {null}

    private fun calculateTip(amount: Double, tipPercent: Int) = currencyFormat.format(tipPercent.toDouble() / 100 * amount)

    companion object {
        private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "DE"))
    }

}

private data class ChangeableValue<T>(val value: T, val onChange: (T) -> Unit)