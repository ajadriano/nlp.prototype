<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="S[count(S)=0][NP[(NNP|NNPS) and *[@nsubj]] and VP[VBZ[@cop and @lemma='be'] and count(RB[@neg])=0 and JJ]]">
DataPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true)
</xsl:template>

<xsl:template match="S[count(S)=0][(NNP|NNPS)[@nsubj] and VP[VBZ[@cop and @lemma='be'] and count(RB[@neg])=0 and JJ]]">
DataPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_to_individual">
            <xsl:with-param name="noun" select="(NNP|NNPS)" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        true)
</xsl:template>

<xsl:template match="S[count(S)=0][NP[(NNP|NNPS) and *[@nsubj]] and VP[VBZ[@cop and @lemma='be'] and count(RB[@neg])>0 and JJ]]">
DataPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_phrase_to_individual">
            <xsl:with-param name="NP" select="NP" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        false)
</xsl:template>

<xsl:template match="S[count(S)=0][(NNP|NNPS)[@nsubj] and VP[VBZ[@cop and @lemma='be'] and count(RB[@neg])>0 and JJ]]">
DataPropertyAssertion(
        <xsl:call-template name="adj_to_data_property">
            <xsl:with-param name="adj" select="VP/JJ" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        <xsl:call-template name="noun_to_individual">
            <xsl:with-param name="noun" select="(NNP|NNPS)" />
        </xsl:call-template>
        <xsl:text> </xsl:text>
        false)
</xsl:template>

</xsl:stylesheet>